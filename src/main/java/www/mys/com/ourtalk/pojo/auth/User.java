package www.mys.com.ourtalk.pojo.auth;

import www.mys.com.ourtalk.utils.net.TimeUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(uniqueConstraints =
        {@UniqueConstraint(columnNames = {"userName", "packageName"}, name = "up")}
        , indexes = {@Index(columnList = "createdMark,email,phone", name = "cep")}
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 32)
    private String userName;        //账户	String(unique)
    @Column(nullable = false, length = 32)
    private String nickName;        //昵称	String
    private Integer sex;            //性别	int
    @Column(length = 120)
    private String userDesc;             //个性签名	String
    @Column(nullable = false, length = 32)
    private String pass;        //密码	String
    @Column(length = 6)
    private String checkCode;        //验证码	String
    @Column(length = 64)
    private String email;           //邮箱	String
    @Column(length = 16)
    private String phone;           //电话号码	String
    @Column(length = 120)
    private String photo;          //头像图片url	String
    @Column(length = 120)
    private String backPhoto;          //背景图片url	String
    @Column(nullable = false, length = 120)
    private String packageName;          //包名   String
    private Integer deviceId = -1;            //设备id	int
    private Integer zone;            //地区	int
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer createdMark = TimeUtils.getDayInt();         //创建日期标记
    @Column(length = 16)
    private String lastLoginIP;          //最后登录ip	String
    private Date lastLoginTime;          //最后登录时间 date
    private boolean available;          //账号是否可用
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date createdAt;
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date updatedAt;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id")})
    private List<Role> roles;

    public User() {
    }

    public User(String userName, String nickName, Integer sex, String userDesc
            , String email, String pass, String phone, String photo, String backPhoto
            , String packageName, Integer zone, Date lastLoginTime, boolean available) {
        this.userName = userName;
        this.nickName = nickName;
        this.sex = sex;
        this.userDesc = userDesc;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.photo = photo;
        this.backPhoto = backPhoto;
        this.packageName = packageName;
        this.zone = zone;
        this.lastLoginTime = lastLoginTime;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBackPhoto() {
        return backPhoto;
    }

    public void setBackPhoto(String backPhoto) {
        this.backPhoto = backPhoto;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public Integer getCreatedMark() {
        return createdMark;
    }

    public void setCreatedMark(Integer createdMark) {
        this.createdMark = createdMark;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", userDesc='" + userDesc + '\'' +
                ", pass='" + pass + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", backPhoto='" + backPhoto + '\'' +
                ", packageName='" + packageName + '\'' +
                ", deviceId=" + deviceId +
                ", zone=" + zone +
                ", createdMark=" + createdMark +
                ", lastLoginIP='" + lastLoginIP + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", available=" + available +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", roles=" + roles +
                '}';
    }
}
