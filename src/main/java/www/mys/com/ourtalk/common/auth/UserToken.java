package www.mys.com.ourtalk.common.auth;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UserToken extends UsernamePasswordToken {

    private Integer deviceId;

    public UserToken() {
    }

    public UserToken(String username, String password, Integer deviceId) {
        super(username, password);
        this.deviceId = deviceId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}
