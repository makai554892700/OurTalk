package www.mys.com.ourtalk.service;

import www.mys.com.ourtalk.pojo.auth.Role;

public interface RoleService {

    public Role getRoleByRoleName(String roleName);

    public Role save(Role role);

}
