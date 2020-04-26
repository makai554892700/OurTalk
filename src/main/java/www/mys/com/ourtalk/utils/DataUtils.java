package www.mys.com.ourtalk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import www.mys.com.ourtalk.pojo.Texts;
import www.mys.com.ourtalk.pojo.auth.SessionUser;
import www.mys.com.ourtalk.pojo.auth.User;
import www.mys.com.ourtalk.utils.net.HexUtils;
import www.mys.com.ourtalk.vo.request.UserRequest;
import www.mys.com.ourtalk.vo.response.ResponseText;
import www.mys.com.ourtalk.vo.response.UserResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataUtils {

    public static void main(String[] args) {
    }

    public static List<ResponseText> getResponseTexts(List<Texts> texts) {
        List<ResponseText> result = new ArrayList<>();
        for (Texts data : texts) {
            result.add(new ResponseText(data.getId(), data.getTextType(), data.getTextValue()
                    , data.getGroupName(), data.getGroupMD5(), data.getTextDesc(), data.getTypeName()
                    , data.getIntDesc(), data.getDateDesc(), data.getCreatedAt()
                    , data.getUpdatedAt()));
        }
        return result;
    }

    public static void decodeMessage(String content, MessageBack messageBack) {
        if (content == null || messageBack == null) {
            return;
        }
        JSONArray jsonArray = JSON.parseArray(content);
        JSONObject jsonObject;
        if (jsonArray == null) {
            return;
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            messageBack.objectBack(jsonObject);
        }
    }

    public static interface MessageBack {
        public void objectBack(JSONObject jsonObject);
    }

    //获取临时用户名(唯一)
    public static String getUserName() {
        return HexUtils.getHexStr(6) + System.currentTimeMillis();
    }

    public static User userRequest2User(UserRequest userRequest, String packageName) {
        if (userRequest == null) {
            return null;
        }
        return new User(userRequest.getUserName(), userRequest.getNickName()
                , userRequest.getSex(), userRequest.getMsg(), userRequest.getEmail()
                , userRequest.getPassWord(), userRequest.getPhone(), userRequest.getImgUrl()
                , userRequest.getBackImgUrl(), packageName, userRequest.getZone(), new Date()
                , userRequest.isAvailable());
    }

    public static UserResponse user2UserResponse(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponse(user.getId(), user.getUserName(), user.getNickName()
                , user.getSex(), user.getUserDesc(), user.getEmail(), user.getPhone(), user.getPhoto()
                , user.getBackPhoto(), user.getZone(), user.getLastLoginIP(), user.getLastLoginTime()
                , user.isAvailable(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public static SessionUser dbUser2SessionUser(User user) {
        if (user == null) {
            return null;
        }
        return new SessionUser(user.getId(), user.getUserName());
    }


}
