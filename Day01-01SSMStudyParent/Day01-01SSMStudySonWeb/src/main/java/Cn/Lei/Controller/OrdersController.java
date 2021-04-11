package Cn.Lei.Controller;

import Cn.Lei.Domain.Orders;
import Cn.Lei.Service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 订单控制器
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    /**
     * 未分页
     * @return
     * @throws Exception
     */
//    @RequestMapping("/findAll")
//    public ModelAndView findAll() throws Exception {
//        ModelAndView modelAndView = new ModelAndView();
//        //数据库操作
//        List<Orders> ordersList = ordersService.findAll();
//        //把对象添加到域
//        modelAndView.addObject("ordersList",ordersList);
//        //跳转
//        modelAndView.setViewName("orders-list");
//
//        return modelAndView;
//    }

    /**
     * 分页查询
     * 默认1一页 查询4条
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(
            @RequestParam(name = "pageNum", required = true, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = true, defaultValue = "4") Integer pageSize) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //数据库操作并且分页
        List<Orders> ordersList = ordersService.findAll(pageNum,pageSize);
        //分页对象 他会进行页码 总页数 等等的自动计算
        PageInfo pageInfo = new PageInfo(ordersList);
        //存入域
        modelAndView.addObject("pageInfo",pageInfo);
        //跳转视图
        modelAndView.setViewName("orders-page-list");

        return modelAndView;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //数据库操作
        Orders orders = ordersService.findById(id);
        System.out.println("控制器数据："+orders);
        modelAndView.addObject("orders",orders);
        //跳转到订单详情
        modelAndView.setViewName("orders-show");

        return modelAndView;
    }
}
