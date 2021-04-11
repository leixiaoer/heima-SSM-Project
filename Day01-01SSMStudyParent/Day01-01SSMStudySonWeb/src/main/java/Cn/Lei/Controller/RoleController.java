package Cn.Lei.Controller;

import Cn.Lei.Domain.Permission;
import Cn.Lei.Domain.Role;
import Cn.Lei.Service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色
 */
@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 根据角色id查询角色还没有的权限有哪些
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPermissionById")
    public ModelAndView findPermissionById(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("这里是测试数据："+roleId);
        //1.根据id查询用户  这一步是为了获取指定id加载进域
        Role role = roleService.findById(roleId);
        //2.根据id查询此角色还能添加的权限
        List<Permission> permissionList = roleService.findOtherPermission(roleId);
        //3.添加到域
        modelAndView.addObject("role", role);
        modelAndView.addObject("permissionList", permissionList);
        //4.转发
        modelAndView.setViewName("role-permission-add");

        return modelAndView;
    }


    //给用户添加角色
    @RequestMapping("/addPermissionToRole")
    public String addRoleToUser(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true) String[] PermissionIds) {
        roleService.addPermissionToRole(roleId, PermissionIds);
        return "redirect:/roles/findAll";
    }


    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //数据库操作并且分页
        List<Role> roleList = roleService.findAll();
        //分页对象 他会进行页码 总页数 等等的自动计算
        PageInfo pageInfo = new PageInfo(roleList);
        //存入域
        modelAndView.addObject("roleList", roleList);
        //跳转视图
        modelAndView.setViewName("role-list");

        return modelAndView;
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    private String save(Role role) throws Exception {
        System.out.println("控制器：添加产品");

        //调用方法添加
        roleService.save(role);

        //重定向到角色展示页面
        //重定向 关键字重定向 拿根目录不需要加项目名称
        return "redirect:/roles/findAll";
    }
}
