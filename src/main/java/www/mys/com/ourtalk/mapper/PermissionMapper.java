package www.mys.com.ourtalk.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import www.mys.com.ourtalk.pojo.auth.Permission;

@Repository("permissionMapper")
public interface PermissionMapper extends JpaRepository<Permission, Integer> {

    public Permission getPermissionByPermissionName(String permissionName);

}
