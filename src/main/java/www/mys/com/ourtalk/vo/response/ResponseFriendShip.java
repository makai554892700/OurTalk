package www.mys.com.ourtalk.vo.response;

import java.util.Date;

public class ResponseFriendShip {

    private Integer id;
    private String fromUser;                    //发起账户
    private String toUser;                      //目标账户
    private String descName;                    //备注名
    private String headImg;                     //用户头像(toUser)
    private boolean block;                      //是否拉黑
    private Integer addWay;                      //添加方式
    private String ruleDesc;                    //权限相关
    private Date createdAt;
    private Date updatedAt;

    public ResponseFriendShip() {
    }

    public ResponseFriendShip(String fromUser, String toUser, String descName, String headImg
            , boolean block, Integer addWay, String ruleDesc) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.descName = descName;
        this.headImg = headImg;
        this.block = block;
        this.addWay = addWay;
        this.ruleDesc = ruleDesc;
    }

    public ResponseFriendShip(Integer id, String fromUser, String toUser, String descName, String headImg
            , boolean block, Integer addWay, String ruleDesc, Date createdAt, Date updatedAt) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.descName = descName;
        this.headImg = headImg;
        this.block = block;
        this.addWay = addWay;
        this.ruleDesc = ruleDesc;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescName() {
        return descName;
    }

    public void setDescName(String descName) {
        this.descName = descName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public Integer getAddWay() {
        return addWay;
    }

    public void setAddWay(Integer addWay) {
        this.addWay = addWay;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
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
        return "ResponseFriendShip{" +
                "id=" + id +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", descName='" + descName + '\'' +
                ", headImg='" + headImg + '\'' +
                ", block=" + block +
                ", addWay=" + addWay +
                ", ruleDesc='" + ruleDesc + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
