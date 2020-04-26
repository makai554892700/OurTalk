package www.mys.com.ourtalk.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints =
        {@UniqueConstraint(columnNames = {"fromUser", "toUser"}, name = "ft")}
        , indexes = {@Index(columnList = "block", name = "b")}
)
public class FriendShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 32)
    private String fromUser;                    //发起账户
    @Column(nullable = false, length = 32)
    private String toUser;                      //目标账户
    @Column(length = 32)
    private String descName;                    //备注名(toUser)
    @Column(length = 32)
    private String headImg;                     //用户头像(toUser)
    private boolean block;                      //是否拉黑(fromUser)
    private Integer addWay;                     //添加方式
    private String ruleDesc;                    //权限相关
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date createdAt;
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date updatedAt;

    public FriendShip() {
    }

    public FriendShip(String fromUser, String toUser, String descName, String headImg
            , boolean block, Integer addWay, String ruleDesc) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.descName = descName;
        this.headImg = headImg;
        this.block = block;
        this.addWay = addWay;
        this.ruleDesc = ruleDesc;
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
        return "FriendShip{" +
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
