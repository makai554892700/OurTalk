package www.mys.com.ourtalk.service;

import www.mys.com.ourtalk.pojo.auth.Permission;

public interface PermissionService {

    public Permission getPermissionByPermissionName(String permissionName);

    public Permission save(Permission permission);

}
