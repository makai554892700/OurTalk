package www.mys.com.ourtalk;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import www.mys.com.ourtalk.common.base.DefaultUserConf;
import www.mys.com.ourtalk.common.base.StaticParam;
import www.mys.com.ourtalk.common.repeatsubmit.RePeatSubmitUtils;
import www.mys.com.ourtalk.mapper.MessageHistoryMapper;
import www.mys.com.ourtalk.mapper.UserMapper;
import www.mys.com.ourtalk.pojo.*;
import www.mys.com.ourtalk.pojo.auth.Permission;
import www.mys.com.ourtalk.pojo.auth.Role;
import www.mys.com.ourtalk.pojo.auth.User;
import www.mys.com.ourtalk.pojo.chat.MessageHistory;
import www.mys.com.ourtalk.service.*;
import www.mys.com.ourtalk.utils.LogUtils;
import www.mys.com.ourtalk.utils.chat.BaseChatPOJO;
import www.mys.com.ourtalk.utils.chat.ChatServerUtils;
import www.mys.com.ourtalk.utils.chat.ChatUtils;
import www.mys.com.ourtalk.utils.file.FileUtils;
import www.mys.com.ourtalk.utils.net.MD5Utils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InitJob {

    @Resource(name = "permissionServiceImpl")
    private PermissionService permissionService;
    @Resource(name = "roleServiceImpl")
    private RoleService roleService;
    @Resource(name = "userMapper")
    private UserMapper userMapper;
    @Resource(name = "defaultUserConf")
    private DefaultUserConf defaultUserConf;
    @Resource(name = "sysConfigServiceImpl")
    private SysConfigService sysConfigService;
    @Resource(name = "textsServiceImpl")
    private TextsService textsService;
    @Resource(name = "messageHistoryMapper")
    private MessageHistoryMapper messageHistoryMapper;
    @Autowired
    private RePeatSubmitUtils rePeatSubmitUtils;
    private static final String HOST = "0.0.0.0";
    private static final String SPLIT = "/ws";
    private static final int PORT = 5000;
    private static final ConcurrentHashMap<String, ChannelHandlerContext> allChannel
            = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, String> keyMap = new ConcurrentHashMap<>();
    private ChatServerUtils.ServerBack serverBack = new ChatServerUtils.ServerBack() {
        @Override
        public void onConnect(ChannelHandlerContext ctx) {
        }

        @Override
        public void onDisConnect(ChannelHandlerContext ctx) {
            allChannel.remove(keyMap.get(ctx.name()));
        }

        @Override
        public void onReceiveMessage(ChannelHandlerContext ctx, WebSocketFrame frame) {
            if (frame instanceof TextWebSocketFrame) {
                onTextBack(ctx, (TextWebSocketFrame) frame);
            }
        }
    };

    @PostConstruct
    public void initProject() {
        LogUtils.initRootPath();
        LogUtils.log("Init project.");
        initUser();
        initSysConf();
        new SimpleAsyncTaskExecutor(getClass().getName())
                .execute(() -> initChatServer());
    }

    private void initChatServer() {
        ChatServerUtils chatServerUtils = new ChatServerUtils(HOST, SPLIT, PORT, serverBack);
        while (true) {
            ChatUtils.sleep(1000);
            if (!chatServerUtils.isRunning()) {
                chatServerUtils = new ChatServerUtils(HOST, SPLIT, PORT, serverBack);
            }
        }
    }

    public static void initImgPath() {
        String[] pathSplits = StaticParam.IMG_PATH.split("/");
        StringBuilder imgRootPath = new StringBuilder(StaticParam.ROOT_PATH);
        for (String pathSplit : pathSplits) {
            imgRootPath.append(File.separator).append(pathSplit);
            if (FileUtils.sureDir(imgRootPath.toString()) == null) {
                LogUtils.log("create img rootPath error.path=" + imgRootPath);
                return;
            }
        }
    }

    private void initSysConf() {
        InputStream inputStream;
        try {
            ClassPathResource resource = new ClassPathResource("properties" + File.separator + "SysConfig.properties");
            inputStream = resource.getInputStream();
        } catch (Exception e) {
            LogUtils.log("init SysConf error. classpath:properties/SysConfig.properties file not exists.");
            return;
        }
        FileUtils.readLine(inputStream, new FileUtils.LineBack() {
            @Override
            public void onStart(String fileName) {

            }

            @Override
            public void onLine(String line) {
                String[] kvs = line.split("=");
                if (kvs.length == 2) {
                    SysConfig sysConfig = sysConfigService.getSysConfigByKey(kvs[0]);
                    if (sysConfig == null) {
                        sysConfigService.saveSysConfig(kvs[0], kvs[1]);
                        if (StaticParam.IMG_PATH_KEY.equals(kvs[1])) {
                            StaticParam.IMG_PATH = kvs[1];
                            initImgPath();
                        } else if (StaticParam.IMG_HOST_KEY.equals(kvs[0])) {
                            StaticParam.IMG_HOST = kvs[1];
                        }
                    } else {
                        LogUtils.log("data exists.line=" + line);
                    }
                } else {
                    LogUtils.log("data error.line=" + line);
                }
            }

            @Override
            public void onEnd(String fileName) {

            }
        });
    }

    private void initUser() {
        Permission tempPermission = defaultUserConf.getPermission();
        Permission permission = permissionService.getPermissionByPermissionName(tempPermission.getPermissionName());
        if (permission == null) {
            permission = permissionService.save(tempPermission);
        }
        Role tempRole = defaultUserConf.getRole();
        Role role = roleService.getRoleByRoleName(tempRole.getRoleName());
        if (role == null) {
            List<Permission> permissions = new ArrayList<>();
            permissions.add(permission);
            tempRole.setPermissions(permissions);
            role = roleService.save(tempRole);
        }
        User tempUser = defaultUserConf.getUser();
        tempUser.setPackageName(StaticParam.EMPTY);
        User user = userMapper.getUserByUserNameAndDeviceId(tempUser.getUserName(), tempUser.getDeviceId());
        if (user == null) {
            if (userMapper.insert(tempUser.getId(), tempUser.getEmail(), tempUser.getPhoto(), tempUser.getUserDesc()
                    , tempUser.getNickName(), tempUser.getPass(), tempUser.getUserName()
                    , tempUser.getSex(), tempUser.getUserName(), tempUser.getPackageName()
                    , tempUser.isAvailable()
            ) > 0) {
                List<Role> roleList = new ArrayList<>();
                roleList.add(role);
                tempUser.setRoles(roleList);
                userMapper.save(tempUser);
                LogUtils.log("Insert user ok");
            } else {
                LogUtils.log("Insert user error.");
            }
        } else {
            LogUtils.log("User already exist.");
        }
    }

    private void onTextBack(ChannelHandlerContext ctx, TextWebSocketFrame textFrame) {
        String text = textFrame.text();
        LogUtils.log("onTextBack ctx.name=" + ctx.name());
        LogUtils.log("onTextBack ctx.channel.id=" + ctx.channel().id());
        LogUtils.log("onTextBack text=" + text);
        BaseChatPOJO baseChatPOJO = JSON.parseObject(text, BaseChatPOJO.class);
        if (baseChatPOJO == null || baseChatPOJO.getType() < BaseChatPOJO.Type.HANDSHAKE) {
            LogUtils.log("receive data error. text=" + text);
            return;
        }
        ChannelHandlerContext resultContext;
        MessageHistory messageHistory;
        switch (baseChatPOJO.getType()) {
            case BaseChatPOJO.Type.HANDSHAKE:
                LogUtils.log("register user:" + baseChatPOJO.getData());
                allChannel.put(baseChatPOJO.getData(), ctx);
                keyMap.put(ctx.name(), baseChatPOJO.getData());
                LogUtils.log("register user:" + allChannel.get(baseChatPOJO.getData()));
                onUserConnect(baseChatPOJO.getData());
                break;
            case BaseChatPOJO.Type.TALK_USER:
            case BaseChatPOJO.Type.TALK_GROUP:
            case BaseChatPOJO.Type.ADD_FRIENDS:
                resultContext = allChannel.get(baseChatPOJO.getToKey());
                if (resultContext == null) {
                    LogUtils.log("user not connect.baseChatPOJO=" + baseChatPOJO);
                    LogUtils.log("user not connect.user=" + baseChatPOJO.getToKey());
                    messageHistory = new MessageHistory(
                            MD5Utils.MD5(baseChatPOJO.getToKey(), false)
                            , JSON.toJSONString(baseChatPOJO));
                    try {
                        messageHistoryMapper.save(messageHistory);
                    } catch (Exception e) {
                    }
                    return;
                }
                ChatUtils.sendText(-1, resultContext.channel(), JSON.toJSONString(baseChatPOJO));
                break;
        }
    }

    private void onUserConnect(String userKey) {
        new Thread(() -> {
            ChannelHandlerContext resultContext;
            Page<MessageHistory> messageHistories;
            do {
                messageHistories = messageHistoryMapper.getByUserKey(MD5Utils.MD5(userKey, false)
                        , PageRequest.of(0, 1000));
                for (MessageHistory messageHistory : messageHistories) {
                    resultContext = allChannel.get(userKey);
                    if (resultContext == null) {
                        LogUtils.log("context is null.");
                        return;
                    }
                    ChatUtils.sendData(resultContext.channel(), messageHistory.getMessageValue()
                            , future -> {
                                if (future.isSuccess()) {
                                    messageHistoryMapper.delete(messageHistory);
                                }
                            });
                }
            } while (messageHistories.hasNext());
        }).start();
    }

}
