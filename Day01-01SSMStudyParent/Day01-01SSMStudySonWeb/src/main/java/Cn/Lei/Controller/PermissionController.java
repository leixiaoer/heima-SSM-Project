package Cn.Lei.Controller;

import Cn.Lei.Domain.Permission;
import Cn.Lei.Domain.Role;
import Cn.Lei.Service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 权限类的控制器
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAlla() throws Exception{
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        System.out.println("控制器：查询所有");

        //从数据库查询数据
        List<Permission> permissionList = permissionService.findAll();

        //把对象加载进ModelAndView，也会自动加载进request域中
        mv.addObject("permissionList",permissionList);

        //跳转页面  这里也会经过视图解析器
        mv.setViewName("permission-list");

        return mv;
    }


    @RequestMapping("/save")
    private String save(Permission permission) throws Exception {
        System.out.println("控制器：添加产品");

        //调用方法添加
        permissionService.savePermission(permission);

        //重定向到产品展示页面
        //重定向 关键字重定向 拿根目录不需要加项目名称
        return "redirect:/permission/findAll";
    }

}
