package Cn.Lei.Service.Impl;

import Cn.Lei.Dao.IRoleDao;
import Cn.Lei.Domain.Permission;
import Cn.Lei.Domain.Role;
import Cn.Lei.Service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    /*
    添加用户
     */
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        /**
         * 多个权限就循环添加
         */
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }
}
