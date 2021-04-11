package Cn.Lei.Dao;

import Cn.Lei.Domain.Permission;
import Cn.Lei.Domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {

    /**
     * 用于展现用户的角色和权限
     * 查询用户id对应的所有的角色
     * * column为数据库字段名，
     * * property为实体类属性名
     *
     * @param userId
     * @return
     * @throws Exception 查询角色关联表中用户id对应的所有角色id  然后再获取是哪些角色
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = java.util.List.class,
                    many = @Many(select = "Cn.Lei.Dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    /**
     * 查询所有的角色
     *
     * @return
     * @throws Exception
     */
    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    /**
     * 添加角色
     *
     * @param role
     * @throws Exception
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role) throws Exception;

    /**
     * 根据id查询
     *
     * @param
     * @return
     */
    @Select("select * from role where id = #{id}")
    @Results({@Result(id = true, column = "id", property = "id")})
    Role findById(String id);

    /**
     * 根据id查询角色还能添加的权限
     *
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermission(String roleId);

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
