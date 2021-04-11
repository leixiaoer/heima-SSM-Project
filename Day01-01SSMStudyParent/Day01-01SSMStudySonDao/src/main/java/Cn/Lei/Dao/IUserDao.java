package Cn.Lei.Dao;

import Cn.Lei.Domain.Role;
import Cn.Lei.Domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户操作
 */
@Repository
public interface IUserDao {

    /**
     * 用户登录
     * 多表操作
     * id为是否为主键
     * column为数据库字段名，
     * property为实体类属性名
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class,
                    many = @Many(select = "Cn.Lei.Dao.IRoleDao.findRoleByUserId")) })
    public UserInfo findByUsername(String username) throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @Select("select * from users")
    public List<UserInfo> findByUserInfo() throws Exception;

    /**
     * 添加用户
     * @param user
     * @throws Exception
     */
    @Insert("insert into users(email,username,password,phoneNum,status) " +
            "values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo user) throws Exception;


    /**
     * 查询用户详细信息
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "Cn.Lei.Dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findById(String id) throws Exception;

    /**
     * 根据id查询用户还能添加的角色
     * @param userId
     * @return
     */
    @Select("select * from  role where id not in (select roleId from users_role where userId = #{userId})")
    public List<Role> findOtherRoles(String userId);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleId 数组循环插入 那边是roleIds  这边给的值是roleId
     */
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
