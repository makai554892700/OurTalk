package www.mys.com.ourtalk.vo.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RequestFriendShip {

    @NotEmpty(message = "发起账户不能为空")
    @Size(max = 32, min = 5, message = "发起账户必须大于5小于32")
    private String fromUser;                    //发起账户
    @NotEmpty(message = "目标账户不能为空")
    @Size(max = 32, min = 5, message = "目标账户必须大于5小于32")
    private String toUser;                      //目标账户
    @Size(max = 32, message = "备注名必须小于32")
    private String descName;                    //备注名
    private String headImg;                     //用户头像(toUser)
    private boolean block;                      //是否拉黑
    private Integer addWay;                      //添加方式
    private String ruleDesc;                    //权限相关

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

    @Override
    public String toString() {
        return "RequestFriendShip{" +
                "fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", descName='" + descName + '\'' +
                ", headImg='" + headImg + '\'' +
                ", block=" + block +
                ", addWay=" + addWay +
                ", ruleDesc='" + ruleDesc + '\'' +
                '}';
    }
}
