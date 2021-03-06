package www.mys.com.ourtalk.vo.response;

import java.util.Date;

public class ResponseAddFriend {

    private Integer id;
    private String nickName;                    //用户昵称
    private String fromUser;                    //发起账户
    private String toUser;                      //目标账户
    private String addMsg;                      //请求数据
    private String backMsg;                     //回复数据
    private Integer lastAddId;                  //上个请求Id
    private Integer addWay;                     //添加方式
    private boolean send;                       //是否送达
    private boolean backSend;                   //回复是否送达
    private Date createdAt;
    private Date updatedAt;

    public ResponseAddFriend() {
    }

    public ResponseAddFriend(String fromUser, String toUser, String addMsg, String backMsg, Integer lastAddId
            , Integer addWay, boolean send, boolean backSend) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.addMsg = addMsg;
        this.backMsg = backMsg;
        this.lastAddId = lastAddId;
        this.addWay = addWay;
        this.send = send;
        this.backSend = backSend;
    }

    public ResponseAddFriend(Integer id, String nickName, String fromUser, String toUser, String addMsg, String backMsg
            , Integer lastAddId, Integer addWay, boolean send, boolean backSend, Date createdAt, Date updatedAt) {
        this.id = id;
        this.nickName = nickName;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.addMsg = addMsg;
        this.backMsg = backMsg;
        this.lastAddId = lastAddId;
        this.addWay = addWay;
        this.send = send;
        this.backSend = backSend;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public boolean isBackSend() {
        return backSend;
    }

    public void setBackSend(boolean backSend) {
        this.backSend = backSend;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ResponseAddFriend{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", addMsg='" + addMsg + '\'' +
                ", backMsg='" + backMsg + '\'' +
                ", lastAddId=" + lastAddId +
                ", addWay=" + addWay +
                ", send=" + send +
                ", backSend=" + backSend +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
