package www.mys.com.ourtalk.api;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import www.mys.com.ourtalk.vo.request.RequestAddFriend;
import www.mys.com.ourtalk.vo.response.ResponseAddFriend;
import www.mys.com.ourtalk.vo.response.ResponseFriendShip;
import www.mys.com.ourtalk.vo.response.Result;

import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("/api/basesb/friend")
public interface FriendApi {

    @GetMapping(value = "/getFriends")
    @RequiresUser
    public Result<ArrayList<ResponseFriendShip>> getFriends() throws Exception;

    @GetMapping(value = "/getFriendRequest")
    @RequiresUser
    public Result<ArrayList<ResponseAddFriend>> getFriendRequest() throws Exception;

    @PostMapping(value = "/addFriend")
    @RequiresUser
    public Result<ResponseAddFriend> addFriend(@RequestBody @Valid RequestAddFriend requestData
            , BindingResult bindingResult) throws Exception;

    @PostMapping(value = "/backFriend/{addFriendId}")
    @RequiresUser
    public Result<ArrayList<ResponseAddFriend>> backFriend(@PathVariable("addFriendId") Integer addFriendId
            , @RequestBody @Valid RequestAddFriend requestData
            , BindingResult bindingResult) throws Exception;

    @GetMapping(value = "/accessFriend/{addFriendId}")
    @RequiresUser
    public Result<String> accessFriend(@PathVariable("addFriendId") Integer addFriendId) throws Exception;

    @GetMapping(value = "/refuseFriend/{addFriendId}")
    @RequiresUser
    public Result<String> refuseFriend(@PathVariable("addFriendId") Integer addFriendId) throws Exception;

}
