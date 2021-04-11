package Cn.Lei.Service.Impl;

import Cn.Lei.Dao.IPermissionDao;
import Cn.Lei.Domain.Permission;
import Cn.Lei.Service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 查询所有权限操作
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void savePermission(Permission permission) throws Exception {
        permissionDao.savePermission(permission);
    }
}
