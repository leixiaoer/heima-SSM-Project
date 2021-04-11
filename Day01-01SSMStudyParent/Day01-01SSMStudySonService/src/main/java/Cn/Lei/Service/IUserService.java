package Cn.Lei.Service;

import Cn.Lei.Domain.Role;
import Cn.Lei.Domain.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    public List<UserInfo> findByUserInfo() throws Exception;

    /**
     * 添加用户
     * @param user
     * @throws Exception
     */
    public void save(UserInfo user) throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    public UserInfo findById(String id) throws Exception;


    /**
     * 根据id查询用户还能添加的角色
     * @param userId
     * @return
     */
    List<Role> findOtherRoles(String userId);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(String userId, String[] roleIds);
}
