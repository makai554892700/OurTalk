package www.mys.com.ourtalk.service.impl;

import org.springframework.stereotype.Service;
import www.mys.com.ourtalk.mapper.PermissionMapper;
import www.mys.com.ourtalk.pojo.auth.Permission;
import www.mys.com.ourtalk.service.PermissionService;

import javax.annotation.Resource;


@Service("permissionServiceImpl")
public class PermissionServiceImpl implements PermissionService {

    @Resource(name = "permissionMapper")
    private PermissionMapper permissionMapper;

    @Override
    public Permission getPermissionByPermissionName(String permissionName) {
        return permissionMapper.getPermissionByPermissionName(permissionName);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionMapper.save(permission);
    }
}
