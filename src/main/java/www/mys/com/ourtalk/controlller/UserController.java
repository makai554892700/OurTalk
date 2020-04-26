package www.mys.com.ourtalk.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import www.mys.com.ourtalk.api.UserApi;
import www.mys.com.ourtalk.common.base.StaticParam;
import www.mys.com.ourtalk.common.encrypt.Decrypt;
import www.mys.com.ourtalk.common.encrypt.Encrypt;
import www.mys.com.ourtalk.pojo.auth.User;
import www.mys.com.ourtalk.service.UserService;
import www.mys.com.ourtalk.utils.DataUtils;
import www.mys.com.ourtalk.utils.ResultUtils;
import www.mys.com.ourtalk.utils.net.IPUtils;
import www.mys.com.ourtalk.vo.request.RequestData;
import www.mys.com.ourtalk.vo.request.UserRequest;
import www.mys.com.ourtalk.vo.response.Result;
import www.mys.com.ourtalk.vo.response.UserResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController implements UserApi {

    @Resource(name = "userServiceImpl")
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @Override
    @Encrypt
    @Decrypt
    public Result<UserResponse> register(@RequestBody @Valid UserRequest userRequest
            , BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception(bindingResult.getFieldError().getDefaultMessage());
        }
        User user = DataUtils.userRequest2User(userRequest, StaticParam.EMPTY);
        user.setAvailable(true);
        User result = userService.register(user, IPUtils.getIP(request)
        );
        if (result == null) {
            return ResultUtils.field(null);
        }
        return ResultUtils.succeed(DataUtils.user2UserResponse(result));
    }

    @Override
    @Encrypt
    @Decrypt
    public Result<UserResponse> login(@RequestBody @Valid UserRequest userRequest
            , BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception(bindingResult.getFieldError().getDefaultMessage());
        }
        User result = userService.login(DataUtils.userRequest2User(userRequest, StaticParam.EMPTY)
                , userRequest.isRember(), userRequest.getDeviceType()
                , IPUtils.getIP(request));
        if (result == null) {
            return ResultUtils.field(null);
        }
        return ResultUtils.succeed(DataUtils.user2UserResponse(result));
    }

    @Override
    @Encrypt
    @Decrypt
    public Result<String> logout() throws Exception {
        return ResultUtils.succeed(userService.logout());
    }

    @Override
    @Encrypt
    @Decrypt
    public Result<UserResponse> getUserInfo() throws Exception {
        User user = userService.commonGetUser();
        return ResultUtils.succeed(new UserResponse(user.getId(), user.getUserName()
                , user.getNickName(), user.getSex(), user.getUserDesc(), user.getEmail()
                , user.getPhone(), user.getPhoto(), user.getBackPhoto(), user.getZone()
                , user.getLastLoginIP(), user.getLastLoginTime(), user.isAvailable(), user.getCreatedAt()
                , user.getUpdatedAt()));
    }

    @Override
    @Encrypt
    @Decrypt
    public Result<UserResponse> queryUser(String userName) throws Exception {
        User user = userService.getUserByName(userName);
        return ResultUtils.succeed(DataUtils.user2UserResponse(user));
    }

    @Override
    @Encrypt
    @Decrypt
    public Result<UserResponse> update(@RequestBody @Valid UserRequest userRequest
            , BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception(bindingResult.getFieldError().getDefaultMessage());
        }
        User result = userService.update(DataUtils.userRequest2User(userRequest, StaticParam.EMPTY));
        if (result == null) {
            return ResultUtils.field(null);
        }
        return ResultUtils.succeed(DataUtils.user2UserResponse(result));
    }

    @Override
    @Encrypt
    @Decrypt
    public Result<Page<UserResponse>> getUsers(Integer page, Integer count) throws Exception {
        return ResultUtils.succeed(userService.getUsers(page, count));
    }

    @Override
    @Encrypt
    @Decrypt
    public void delUsers(@RequestBody @Valid RequestData<List<Integer>> ids, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception(bindingResult.getFieldError().getDefaultMessage());
        }
        userService.delUsers(ids.getData());
    }
}
