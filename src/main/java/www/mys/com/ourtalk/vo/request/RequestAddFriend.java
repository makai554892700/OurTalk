package www.mys.com.ourtalk.vo.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RequestAddFriend {

    @NotEmpty(message = "发起账户不能为空")
    @Size(max = 32, min = 5, message = "发起账户必须大于5小于32")
    private String fromUser;                    //发起账户
    @NotEmpty(message = "目标账户不能为空")
    @Size(max = 32, min = 5, message = "目标账户必须大于5小于32")
    private String toUser;                      //目标账户
    private String addMsg;                      //请求数据
    private String backMsg;                     //回复数据
    private Integer lastAddId;                  //上个请求Id
    private Integer addWay;                     //添加方式

    public RequestAddFriend() {
    }

    public RequestAddFriend(String fromUser, String toUser, String addMsg
            , String backMsg, Integer lastAddId, Integer addWay) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.addMsg = addMsg;
        this.backMsg = backMsg;
        this.lastAddId = lastAddId;
        this.addWay = addWay;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getAddMsg() {
        return addMsg;
    }

    public void setAddMsg(String addMsg) {
        this.addMsg = addMsg;
    }

    public String getBackMsg() {
        return backMsg;
    }

    public void setBackMsg(String backMsg) {
        this.backMsg = backMsg;
    }

    public Integer getLastAddId() {
        return lastAddId;
    }

    public void setLastAddId(Integer lastAddId) {
        this.lastAddId = lastAddId;
    }

    public Integer getAddWay() {
        return addWay;
    }

    public void setAddWay(Integer addWay) {
        this.addWay = addWay;
    }

    @Override
    public String toString() {
        return "RequestAddFriend{" +
                "fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", addMsg='" + addMsg + '\'' +
                ", backMsg='" + backMsg + '\'' +
                ", lastAddId=" + lastAddId +
                ", addWay=" + addWay +
                '}';
    }
}
