package Cn.Lei.Controller;

import Cn.Lei.Domain.Orders;
import Cn.Lei.Domain.Product;
import Cn.Lei.Domain.Role;
import Cn.Lei.Domain.UserInfo;
import Cn.Lei.Service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    /**
     * 根据id查询用户角色和还能再添加的角色
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name="id" ,required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.根据id查询用户
        UserInfo userInfo = userService.findById(userId);
        //2.根据id查询可以添加的角色
        List<Role> otherRoles= userService.findOtherRoles(userId);
        //3.添加到域
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        //4.设置转发的网页
        mv.setViewName("user-role-add");

        return mv;
    }


    //给用户添加角色
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:/user/findAll";
    }

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //数据库操作并且分页
        List<UserInfo> userList = userService.findByUserInfo();
        //分页对象 他会进行页码 总页数 等等的自动计算
        PageInfo pageInfo = new PageInfo(userList);
        //存入域
        modelAndView.addObject("userList",userList);
        //跳转视图
        modelAndView.setViewName("user-list");

        return modelAndView;
    }

    /**
     * 添加用户
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    private String save(UserInfo user) throws Exception {
        System.out.println("控制器：添加产品");

        //调用方法添加
        userService.save(user);

        //重定向到产品展示页面
        //重定向 关键字重定向 拿根目录不需要加项目名称
        return "redirect:/user/findAll";
    }

    /**
     * 查询详细信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //数据库操作
        UserInfo user = userService.findById(id);
        System.out.println("控制器数据："+user);
        modelAndView.addObject("user",user);
        //跳转到订单详情
        modelAndView.setViewName("user-show");

        return modelAndView;
    }
}
