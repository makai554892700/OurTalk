package www.mys.com.ourtalk.vo.response;

import java.util.Date;

public class UserResponse {

    private Integer id;
    private String userName;        //账户	String(unique)
    private String nickName;        //昵称	String
    private Integer sex;            //性别	int
    private String userDesc;             //个性签名	String
    private String email;           //邮箱	String
    private String phone;           //电话号码	String
    private String photo;          //头像图片url	String
    private String backPhoto;          //背景图片url	String
    private Integer zone;            //地区	int
    private String lastLoginIP;          //最后登录ip	String
    private Date lastLoginTime;          //最后登录时间 date
    private boolean available;          //账号是否可用
    private Date createdAt;
    private Date updatedAt;

    public UserResponse() {
    }

    public UserResponse(Integer id, String userName, String nickName, Integer sex
            , String userDesc, String email, String phone, String photo, String backPhoto
            , Integer zone, String lastLoginIP, Date lastLoginTime, boolean available, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.sex = sex;
        this.userDesc = userDesc;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
        this.backPhoto = backPhoto;
        this.zone = zone;
        this.lastLoginIP = lastLoginIP;
        this.lastLoginTime = lastLoginTime;
        this.available = available;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
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

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", userDesc='" + userDesc + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", backPhoto='" + backPhoto + '\'' +
                ", zone=" + zone +
                ", lastLoginIP='" + lastLoginIP + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", available=" + available +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
