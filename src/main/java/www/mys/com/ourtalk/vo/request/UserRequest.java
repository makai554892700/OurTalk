package www.mys.com.ourtalk.vo.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequest {

    private Integer id;            //id     int
    @NotEmpty(message = "用户名不能为空")
    @Size(max = 32, min = 5, message = "用户名必须大于5小于32")
    private String userName;        //账户	String(unique)
    private String nickName;        //昵称	String
    private Integer sex;            //性别	int
    private String msg;             //个性签名	String
    private int zone;             //地区	String
    @Size(max = 100, min = 6, message = "密码长度必须小于100大于5")
    private String passWord;        //密码	String
    private String email;           //邮箱	String
    private String phone;           //电话号码	String
    private String imgUrl;          //头像图片url	String
    private String backImgUrl;          //背景图片url	String
    private boolean rember;       //是否记住我
    @NotNull(message = "设备信息必须上传")
    private Integer deviceType;     //设备类型 0:web/1:Android/2:IOS
    private boolean available;          //账号是否可用
    private String packageName;          //包名   String
    private Integer deviceId = -1;            //设备id	int

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBackImgUrl() {
        return backImgUrl;
    }

    public void setBackImgUrl(String backImgUrl) {
        this.backImgUrl = backImgUrl;
    }

    public boolean isRember() {
        return rember;
    }

    public void setRember(boolean rember) {
        this.rember = rember;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    @Override
    public String toString() {
        return "UserRequest{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", msg='" + msg + '\'' +
                ", zone=" + zone +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", backImgUrl='" + backImgUrl + '\'' +
                ", rember=" + rember +
                ", deviceType=" + deviceType +
                ", available=" + available +
                ", packageName='" + packageName + '\'' +
                ", deviceId=" + deviceId +
                '}';
    }
}
