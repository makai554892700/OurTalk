package www.mys.com.ourtalk.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import www.mys.com.ourtalk.api.FriendApi;
import www.mys.com.ourtalk.common.base.StaticParam;
import www.mys.com.ourtalk.common.encrypt.Decrypt;
import www.mys.com.ourtalk.common.encrypt.Encrypt;
import www.mys.com.ourtalk.common.exception.BaseException;
import www.mys.com.ourtalk.pojo.AddFriend;
import www.mys.com.ourtalk.pojo.auth.User;
import www.mys.com.ourtalk.service.FriendService;
import www.mys.com.ourtalk.service.UserService;
import www.mys.com.ourtalk.utils.ResultUtils;
import www.mys.com.ourtalk.vo.request.RequestAddFriend;
import www.mys.com.ourtalk.vo.response.ResponseAddFriend;
import www.mys.com.ourtalk.vo.response.ResponseFriendShip;
import www.mys.com.ourtalk.vo.response.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class FriendController implements FriendApi {

    @Resource(name = "userServiceImpl")
    private UserService userService;
    @Resource(name = "friendServiceImpl")
    private FriendService friendService;
    @Autowired
    private HttpServletRequest request;

    @Override
    @Encrypt
    @Decrypt
    public Result<ArrayList<ResponseFriendShip>> getFriends() throws Exception {
        return ResultUtils.succeed(friendService.getFriends(userService.commonGetUser()));
    }

    @Override
    @Encrypt
    @Decrypt
    public Result<ArrayList<ResponseAddFriend>> getFriendRequest() throws Exception {
        return ResultUtils.succeed(friendService.getFriendRequest(userService.commonGetUser()));
    }

    @Override
    @Encrypt
    @Decrypt
    public Result<ResponseAddFriend> addFriend(@RequestBody @Valid RequestAddFriend requestData
            , BindingResult bindingResult) throws Exception {
        if (bindingResult != null && bindingResult.hasErrors()) {
            throw new Exception(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.succeed(friendService.addFriend(requestData
                , userService.commonGetUser()));
    }

    @Override
    @Encrypt
    @Decrypt
    public Result<ArrayList<ResponseAddFriend>> backFriend(Integer addFriendId
            , @RequestBody @Valid RequestAddFriend requestData
            , BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception(bindingResult.getFieldError().getDefaultMessage());
        }
        User user = userService.commonGetUser();
        AddFriend addFriend = friendService.getById(addFriendId);
        if (addFriend == null) {
            throw new BaseException("非法请求 backFriend=" + user.getUserName(), -1);
        }
        int findId;
        if (requestData.getFromUser().equals(user.getUserName())) {
            Result<ResponseAddFriend> result = addFriend(new RequestAddFriend(
                    requestData.getFromUser(), requestData.getToUser()
                    , requestData.getBackMsg(), null, addFriend.getId()
                    , addFriend.getAddWay()
            ), null);
            findId = result.getData().getId();
        } else if (requestData.getToUser().equals(user.getUserName())) {
            friendService.updateBackMsg(addFriend.getId(), requestData.getBackMsg());
            findId = addFriend.getId();
        } else {
            throw new BaseException("非法请求 userName=" + user.getUserName(), -1);
        }
        return ResultUtils.succeed(friendService.getAddFriends(findId));
    }

    @Override
    @Encrypt
    @Decrypt
    public Result<String> accessFriend(Integer addFriendId) throws Exception {
        return ResultUtils.succeed(friendService.accessFriend(addFriendId, userService.commonGetUser()));
    }

    @Override
    @Encrypt
    @Decrypt
    public Result<String> refuseFriend(Integer addFriendId) throws Exception {
        return ResultUtils.succeed(friendService.refuseFriend(addFriendId, userService.commonGetUser()));
    }

}
