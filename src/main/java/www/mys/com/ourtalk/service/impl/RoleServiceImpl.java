package www.mys.com.ourtalk.service.impl;

import org.springframework.stereotype.Service;
import www.mys.com.ourtalk.mapper.RoleMapper;
import www.mys.com.ourtalk.pojo.auth.Role;
import www.mys.com.ourtalk.service.RoleService;

import javax.annotation.Resource;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

    @Resource(name = "roleMapper")
    private RoleMapper roleMapper;

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleMapper.getRoleByRoleName(roleName);
    }

    @Override
    public Role save(Role role) {
        return roleMapper.save(role);
    }
}
