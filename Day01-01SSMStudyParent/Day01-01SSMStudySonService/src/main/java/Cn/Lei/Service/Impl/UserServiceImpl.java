package Cn.Lei.Service.Impl;

import Cn.Lei.Dao.IUserDao;
import Cn.Lei.Domain.Role;
import Cn.Lei.Domain.UserInfo;
import Cn.Lei.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    /**
     * Spring Security  密码加密
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 用户登录 验证 权限 退出等操作
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
        //  User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(),userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));

        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述  读取权限等
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @Override
    public List<UserInfo> findByUserInfo() throws Exception {
        return userDao.findByUserInfo();
    }

    /**
     * 添加用户
     * @param user
     * @throws Exception
     */
    @Override
    public void save(UserInfo user) throws Exception {
        //密码加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userDao.save(user);
    }


    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userId) {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        /**
         * 到控制器获取集合 循环添加角色 根据你选择的角色
         * 注意这边给的是 循环后的 roleId  在dao层就要使用roleId
         */
        for(String roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
