package www.mys.com.ourtalk.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import www.mys.com.ourtalk.common.base.StaticParam;
import www.mys.com.ourtalk.common.exception.BaseException;
import www.mys.com.ourtalk.mapper.AddFriendMapper;
import www.mys.com.ourtalk.mapper.FriendShipMapper;
import www.mys.com.ourtalk.mapper.UserMapper;
import www.mys.com.ourtalk.pojo.AddFriend;
import www.mys.com.ourtalk.pojo.FriendShip;
import www.mys.com.ourtalk.pojo.auth.User;
import www.mys.com.ourtalk.service.FriendService;
import www.mys.com.ourtalk.vo.request.RequestAddFriend;
import www.mys.com.ourtalk.vo.response.ResponseAddFriend;
import www.mys.com.ourtalk.vo.response.ResponseFriendShip;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service("friendServiceImpl")
public class FriendServiceImpl implements FriendService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;
    @Resource(name = "addFriendMapper")
    private AddFriendMapper addFriendMapper;
    @Resource(name = "friendShipMapper")
    private FriendShipMapper friendShipMapper;

    @Override
    public ResponseAddFriend addFriend(RequestAddFriend requestData, User user) {
        if (!requestData.getFromUser().equals(user.getUserName())
                || requestData.getFromUser().equals(requestData.getToUser())) {
            throw new BaseException("非法请求 userName=" + user.getUserName(), -1);
        }
        if (requestData.getLastAddId() != null && requestData.getLastAddId() > 0) {
            AddFriend lastAddFriend = addFriendMapper.getById(requestData.getLastAddId());
            if (lastAddFriend == null) {
                throw new BaseException("非法请求 lastAddFriend null. userName=" + user.getUserName(), -1);
            } else if (!user.getUserName().equals(lastAddFriend.getFromUser())) {
                throw new BaseException("非法请求 not same user. userName=" + user.getUserName(), -1);
            }
        }
        User toUser = userMapper.getUserByUserName(requestData.getToUser());
        if (toUser == null) {
            throw new BaseException("非法请求 not to user. userName=" + requestData.getToUser(), -1);
        }
        AddFriend addFriend = new AddFriend(user.getNickName(), requestData.getFromUser()
                , requestData.getToUser(), requestData.getAddMsg(), null
                , requestData.getAddWay(), requestData.getLastAddId(), false, false);
        addFriendMapper.save(addFriend);
        return new ResponseAddFriend(addFriend.getId(), addFriend.getNickName(), addFriend.getFromUser(), addFriend.getToUser()
                , addFriend.getAddMsg(), addFriend.getBackMsg(), addFriend.getLastAddId()
                , addFriend.getAddWay(), addFriend.isSend(), addFriend.isBackSend()
                , addFriend.getCreatedAt(), addFriend.getUpdatedAt());
    }

    @Override
    public AddFriend getById(Integer addFriendId) {
        return addFriendMapper.getById(addFriendId);
    }

    @Override
    public void updateBackMsg(Integer addFriendId, String backMsg) {
        addFriendMapper.updateBackMsg(addFriendId, backMsg);
    }

    @Override
    public ArrayList<ResponseAddFriend> getAddFriends(Integer addFriendId) {
        ArrayList<ResponseAddFriend> result = new ArrayList<>();
        AddFriend addFriend = addFriendMapper.getById(addFriendId);
        if (addFriend != null) {
            result.add(new ResponseAddFriend(
                    addFriend.getId(), addFriend.getNickName(), addFriend.getFromUser()
                    , addFriend.getToUser(), addFriend.getAddMsg()
                    , addFriend.getBackMsg(), addFriend.getLastAddId(), addFriend.getAddWay()
                    , addFriend.isSend(), addFriend.isBackSend()
                    , addFriend.getCreatedAt(), addFriend.getUpdatedAt()
            ));
            if (addFriend.getLastAddId() > 0) {
                result.addAll(getAddFriends(addFriend.getLastAddId()));
            }
        }
        return result;
    }

    @Override
    public void deleteById(Integer addFriendId) {
        addFriendMapper.deleteById(addFriendId);
    }

    @Override
    public ArrayList<ResponseFriendShip> getFriends(User user) {
        Page<FriendShip> friendShips = friendShipMapper.getByFromUser(user.getUserName()
                , PageRequest.of(0, 1000));
        ArrayList<ResponseFriendShip> result = new ArrayList<>();
        for (FriendShip friendShip : friendShips) {
            result.add(new ResponseFriendShip(friendShip.getId(), friendShip.getFromUser()
                    , friendShip.getToUser(), friendShip.getDescName(), friendShip.getHeadImg()
                    , friendShip.isBlock(), friendShip.getAddWay(), friendShip.getRuleDesc()
                    , friendShip.getCreatedAt(), friendShip.getUpdatedAt()));
        }
        return result;
    }

    @Override
    public ArrayList<ResponseAddFriend> getFriendRequest(User user) {
        Page<AddFriend> addFriends = addFriendMapper.getByToUserAndLastAddIdIsNull(user.getUserName()
                , PageRequest.of(0, 1000));
        ArrayList<ResponseAddFriend> result = new ArrayList<>();
        for (AddFriend addFriend : addFriends) {
            result.add(new ResponseAddFriend(addFriend.getId(), addFriend.getNickName(), addFriend.getFromUser()
                    , addFriend.getToUser(), addFriend.getAddMsg(), addFriend.getBackMsg()
                    , addFriend.getLastAddId(), addFriend.getAddWay(), addFriend.isSend()
                    , addFriend.isBackSend(), addFriend.getCreatedAt(), addFriend.getUpdatedAt()));
        }
        return result;
    }

    @Override
    public String accessFriend(Integer addFriendId, User user) {
        AddFriend addFriend = getAddFriend(addFriendId, user);
        User fromUser = userMapper.getUserByUserName(addFriend.getFromUser());
        FriendShip friendShip = friendShipMapper.getByFromUserAndToUser(addFriend.getToUser(), addFriend.getFromUser());
        if (friendShip == null) {
            friendShipMapper.save(new FriendShip(addFriend.getToUser()
                    , addFriend.getFromUser(), fromUser.getNickName()
                    , fromUser.getPhoto(), false, addFriend.getAddWay()
                    , null));
        }
        friendShip = friendShipMapper.getByFromUserAndToUser(addFriend.getFromUser(), addFriend.getToUser());
        if (friendShip == null) {
            friendShipMapper.save(new FriendShip(addFriend.getFromUser()
                    , addFriend.getToUser(), user.getNickName()
                    , user.getPhoto(), false, addFriend.getAddWay()
                    , null));
        }
        return StaticParam.SUCCESS;
    }

    @Override
    public String refuseFriend(Integer addFriendId, User user) {
        AddFriend addFriend = getAddFriend(addFriendId, user);
        addFriendMapper.deleteById(addFriendId);
        return StaticParam.SUCCESS;
    }

    private AddFriend getAddFriend(Integer addFriendId, User user) {
        AddFriend addFriend = addFriendMapper.getById(addFriendId);
        if (addFriend == null) {
            throw new BaseException("非法请求 backFriend=" + user.getUserName(), -1);
        }
        if (!user.getUserName().equals(addFriend.getToUser())) {
            throw new BaseException("非法请求 backFriend=" + user.getUserName(), -1);
        }
        return addFriend;
    }

}
