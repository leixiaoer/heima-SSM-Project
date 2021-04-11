package Cn.Lei.Service;

import Cn.Lei.Domain.Permission;
import Cn.Lei.Domain.Role;

import java.util.List;

public interface IRoleService {

    /**
     * 查询所有的用户
     * @return
     * @throws Exception
     */
    public List<Role> findAll() throws Exception;

    /**
     * 添加用户
     * @param role
     * @throws Exception
     */
    public void save(Role role) throws Exception;

    /**
     * 根据id查询角色
     * @param roleId
     */
    Role findById(String roleId);

    /**
     * 根据id查询角色还能添加的权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermission(String roleId);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(String roleId, String[] permissionIds);
}
