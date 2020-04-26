package www.mys.com.ourtalk.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import www.mys.com.ourtalk.common.auth.UserToken;
import www.mys.com.ourtalk.common.base.BaseResultEnum;
import www.mys.com.ourtalk.common.base.DefaultUserConf;
import www.mys.com.ourtalk.common.base.StaticParam;
import www.mys.com.ourtalk.common.exception.BaseException;
import www.mys.com.ourtalk.mapper.UserMapper;
import www.mys.com.ourtalk.pojo.auth.SessionUser;
import www.mys.com.ourtalk.pojo.auth.User;
import www.mys.com.ourtalk.service.UserService;
import www.mys.com.ourtalk.utils.DataUtils;
import www.mys.com.ourtalk.vo.response.UserResponse;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;
    @Resource(name = "defaultUserConf")
    private DefaultUserConf defaultUserConf;

    @Override
    public User register(User user, String clientIP) throws Exception {
        User dbUser = userMapper.getUserByUserNameAndDeviceId(user.getUserName(), user.getDeviceId());
        if (dbUser == null) {
            if (user.getPass() == null || user.getPass().length() < 6) {
                throw new BaseException(BaseResultEnum.USER_PASS_ERROR);
            }
            user.setPass(getPass(user.getPass()));
            user.setLastLoginIP(clientIP);
            user.setLastLoginTime(new Date());
            dbUser = userMapper.save(user);
            return dbUser;
        } else {
            throw new BaseException(BaseResultEnum.DOUBLE_USER_NAME);
        }
    }

    @Override
    public User login(User user, boolean isRember, int deviceType, String clientIP) throws Exception {
        User dbUser = userMapper.getUserByUserNameAndDeviceId(user.getUserName(), user.getDeviceId());
        if (dbUser == null) {
            throw new BaseException(BaseResultEnum.NO_USER);
        }
        if (!dbUser.isAvailable()) {
            throw new BaseException(BaseResultEnum.USER_BLOCKED);
        }
        UserToken token = new UserToken(user.getUserName(), getPass(user.getPass()), user.getDeviceId());
        token.setRememberMe(isRember);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        if (deviceType != 0) {
            currentUser.getSession().setTimeout(StaticParam.SHIRO_EVER);//永远记住
        }
        dbUser = userMapper.getUserByUserNameAndDeviceId(user.getUserName(), user.getDeviceId());
        dbUser.setLastLoginTime(new Date());
        dbUser.setLastLoginIP(clientIP);
        userMapper.save(dbUser);
        currentUser.getSession().setAttribute(StaticParam.SHIRO_USER, DataUtils.dbUser2SessionUser(dbUser));
        return dbUser;
    }

    @Override
    public User update(User user) throws Exception {
        System.out.println("user = " + user);
        User dbUser = userMapper.getUserByUserNameAndDeviceId(user.getUserName(), user.getDeviceId());
        if (dbUser != null) {
            dbUser = update(user, false);
            return dbUser;
        } else {
            throw new BaseException(BaseResultEnum.NO_USER);
        }
    }

    @Override
    public String logout() throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            currentUser.logout();
        }
        return StaticParam.OK;
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    private User update(User user, boolean updateRole) {
        User dbUser = userMapper.getUserByUserNameAndDeviceId(user.getUserName(), user.getDeviceId());
        if (!StringUtils.isEmpty(user.getPhoto())) {
            dbUser.setPhoto(user.getPhoto());
        }
        if (!StringUtils.isEmpty(user.getNickName())) {
            dbUser.setNickName(user.getNickName());
        }
        if (!StringUtils.isEmpty(user.getPass())) {
            if (user.getPass() == null || user.getPass().length() < 6) {
                throw new BaseException(BaseResultEnum.USER_PASS_ERROR);
            }
            dbUser.setPass(getPass(user.getPass()));
        }
        if (!StringUtils.isEmpty(user.getEmail())) {
            dbUser.setEmail(user.getEmail());
        }
        if (!StringUtils.isEmpty(user.getUserDesc())) {
            dbUser.setUserDesc(user.getUserDesc());
        }
        if (!StringUtils.isEmpty(user.getSex())) {
            dbUser.setSex(user.getSex());
        }
        if (!StringUtils.isEmpty(user.getPhone())) {
            dbUser.setPhone(user.getPhone());
        }
        dbUser.setAvailable(user.isAvailable());
        if (updateRole && user.getRoles() != null && !user.getRoles().isEmpty()) {
            dbUser.setRoles(user.getRoles());
        }
        return userMapper.save(dbUser);
    }

    public static void main(String[] args) {
        System.out.println("pass = " + new SimpleHash("md5", "admin123", "admin"));
    }

    public String getPass(String pass) {
        return new SimpleHash(defaultUserConf.getEncrypt(), pass, defaultUserConf.getSalt()).toString();
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User commonGetUser() {
        SessionUser sessionUser = (SessionUser) SecurityUtils.getSubject().getSession()
                .getAttribute(StaticParam.SHIRO_USER);
        User user = userMapper.getUserById(sessionUser.getId());
        if (user == null) {
            throw new BaseException(BaseResultEnum.NO_USER);
        }
        return user;
    }

    @Override
    public Page<UserResponse> getUsers(Integer page, Integer count) {
        return userMapper.getAllUsers(PageRequest.of(page, count, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<UserResponse> getUserByIdIn(ArrayList<Integer> ids) {
        return userMapper.getUserByIdIn(ids, PageRequest.of(0, ids.size()));
    }

    @Override
    public void delUsers(List<Integer> ids) {
        userMapper.deleteByIdIn(ids);
    }
}
