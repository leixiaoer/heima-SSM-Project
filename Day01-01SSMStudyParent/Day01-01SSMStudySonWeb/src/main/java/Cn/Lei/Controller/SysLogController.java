package Cn.Lei.Controller;

import Cn.Lei.Domain.SysLog;
import Cn.Lei.Service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.调用dao层查询
        List<SysLog> sysLogList = sysLogService.findAll();
        //2.添加到域
        mv.addObject("sysLogs",sysLogList);

        //3.设置跳转页面
        mv.setViewName("syslog-list");

        return mv;
    }
}
