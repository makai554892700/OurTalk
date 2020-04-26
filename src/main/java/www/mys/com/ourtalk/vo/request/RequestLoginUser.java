package www.mys.com.ourtalk.vo.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RequestLoginUser {
    @NotEmpty(message = "用户名不能为空")
    private String userName;        //账户	String(unique)
    private String nickName;        //昵称	String
    private Integer sex;            //性别	int
    private String userDesc;        //个性签名	String
    @NotEmpty(message = "密码不能为空")
    @Size(max = 100, min = 6, message = "密码长度必须小于100大于5")
    private String pass;            //密码	String
    private String email;           //邮箱	String
    private String phone;           //电话号码	String
    private String photo;           //头像图片url	String

    public RequestLoginUser() {
    }

    public RequestLoginUser(@NotEmpty(message = "用户名不能为空") String userName
            , String nickName, Integer sex, String userDesc
            , @NotEmpty(message = "密码不能为空") @Size(max = 100, min = 6
            , message = "密码长度必须小于100大于5") String pass, String email
            , String phone, String photo) {
        this.userName = userName;
        this.nickName = nickName;
        this.sex = sex;
        this.userDesc = userDesc;
        this.pass = pass;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
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

    @Override
    public String toString() {
        return "RequestLoginUser{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", userDesc='" + userDesc + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
