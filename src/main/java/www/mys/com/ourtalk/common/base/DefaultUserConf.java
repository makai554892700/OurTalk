package www.mys.com.ourtalk.common.base;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import www.mys.com.ourtalk.pojo.auth.Permission;
import www.mys.com.ourtalk.pojo.auth.Role;
import www.mys.com.ourtalk.pojo.auth.User;

@Component("defaultUserConf")
@ConfigurationProperties(prefix = "user.default")
public class DefaultUserConf {

    private User user;
    private Permission permission;
    private Role role;
    private String encrypt;
    private String salt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "DefaultUserConf{" +
                "user=" + user +
                ", permission=" + permission +
                ", role=" + role +
                ", encrypt='" + encrypt + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
