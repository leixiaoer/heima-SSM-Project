package Cn.Lei.Service;

import Cn.Lei.Domain.Permission;

import java.util.List;

/**
 * 权限
 */
public interface IPermissionService {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    public List<Permission> findAll() throws Exception;

    /**
     * 添加权限
     * @param permission
     * @throws Exception
     */
    public void savePermission(Permission permission) throws Exception;
}
