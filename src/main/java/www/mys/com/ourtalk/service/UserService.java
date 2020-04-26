package www.mys.com.ourtalk.service;

import org.springframework.data.domain.Page;
import www.mys.com.ourtalk.pojo.auth.User;
import www.mys.com.ourtalk.vo.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    public User register(User user, String clientIP) throws Exception;

    public User login(User user, boolean remberMe, int deviceType, String clientIP) throws Exception;

    public User update(User user) throws Exception;

    public String logout() throws Exception;

    public User getUserById(Integer id);

    public User getUserByName(String userName);

    public User commonGetUser();

    public Page<UserResponse> getUsers(Integer page, Integer count);

    public Page<UserResponse> getUserByIdIn(ArrayList<Integer> ids);

    public void delUsers(List<Integer> ids);

}
