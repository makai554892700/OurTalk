package www.mys.com.ourtalk.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import www.mys.com.ourtalk.pojo.auth.User;
import www.mys.com.ourtalk.vo.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

@Repository("userMapper")
public interface UserMapper extends JpaRepository<User, Integer> {

    public User getUserById(Integer id);

    public User getUserByUserNameAndDeviceId(String userName, Integer deviceId);

    public User getUserByUserName(String userName);

    public User getUserByUserNameAndPackageName(String userName, String packageName);

    @Transactional //执行自定义sql更新或者删除时必须带
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "insert into user (id, email, photo, user_desc, nick_name, pass, phone, sex, user_name, package_name, available) " +
            "values (:id, :email, :photo, :user_desc, :nick_name, :pass, :phone, :sex, :user_name, :package_name, :available)")
    public Integer insert(@Param("id") Integer id, @Param("email") String email, @Param("photo") String photo,
                          @Param("user_desc") String user_desc, @Param("nick_name") String nickName
            , @Param("pass") String pass, @Param("phone") String phone, @Param("sex") Integer sex
            , @Param("user_name") String userName, @Param("package_name") String packageName
            , @Param("available") boolean available);

    @Transactional //执行自定义sql更新或者删除时必须带
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "update user set id = :newId where id = :oldId")
    public void updateUserId(@Param("oldId") Integer oldId, @Param("newId") Integer newId);

    @Query(value = "select new www.mys.com.ourtalk.vo.response.UserResponse(u.id,u.userName,u.nickName" +
            ",u.sex,u.userDesc,u.email,u.phone,u.photo,u.backPhoto,u.zone,u.lastLoginIP,u.lastLoginTime" +
            ",u.available,u.createdAt,u.updatedAt) from User as u")
    public Page<UserResponse> getAllUsers(Pageable pageable);

    @Query(value = "select new www.mys.com.ourtalk.vo.response.UserResponse(u.id,u.userName,u.nickName" +
            ",u.sex,u.userDesc,u.email,u.phone,u.photo,u.backPhoto,u.zone,u.lastLoginIP,u.lastLoginTime" +
            ",u.available,u.createdAt,u.updatedAt) from User as u where u.id in :ids")
    public Page<UserResponse> getUserByIdIn(@Param("ids") ArrayList<Integer> ids, Pageable pageable);

    @Transactional //执行自定义sql更新或者删除时必须带
    public void deleteByIdIn(List<Integer> ids);

}
