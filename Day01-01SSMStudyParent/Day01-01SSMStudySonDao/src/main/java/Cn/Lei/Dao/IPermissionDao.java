package Cn.Lei.Dao;

import Cn.Lei.Domain.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限接口
 */
@Repository
public interface IPermissionDao {

    /**
     * 用于展现用户的角色和权限
     * 查询角色id  对应的角色信息
     * @param
     * @return
     * @throws Exception
     * 查询角色关联表中用户id对应的所有角色id  然后再获取是哪些角色
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    /**
     * 查询所有操作
     * @return
     * @throws Exception
     */
    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    /**
     * 添加权限
     * @param permission
     * @throws Exception
     */
    @Select("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void savePermission(Permission permission) throws Exception;
}
