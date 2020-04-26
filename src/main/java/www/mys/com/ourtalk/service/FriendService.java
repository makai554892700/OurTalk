package www.mys.com.ourtalk.service;

import org.springframework.data.domain.Page;
import www.mys.com.ourtalk.pojo.AddFriend;
import www.mys.com.ourtalk.pojo.auth.User;
import www.mys.com.ourtalk.vo.request.RequestAddFriend;
import www.mys.com.ourtalk.vo.response.ResponseAddFriend;
import www.mys.com.ourtalk.vo.response.ResponseFriendShip;
import www.mys.com.ourtalk.vo.response.UserResponse;

import java.util.ArrayList;

public interface FriendService {

    public ResponseAddFriend addFriend(RequestAddFriend requestData, User user);

    public AddFriend getById(Integer addFriendId);

    public void updateBackMsg(Integer addFriendId, String backMsg);

    public ArrayList<ResponseAddFriend> getAddFriends(Integer addFriendId);

    public void deleteById(Integer addFriendId);

    public ArrayList<ResponseFriendShip> getFriends(User user);

    public ArrayList<ResponseAddFriend> getFriendRequest(User user);

    public String accessFriend(Integer addFriendId, User user);

    public String refuseFriend(Integer addFriendId, User user);

}
