package cn.edu.lingnan.goodf.web;

import cn.edu.lingnan.goodf.common.utils.R;
import cn.edu.lingnan.goodf.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class RegistrationControllerWeb {
    @Autowired
    private RegistrationService registrationService;

    /**
     *   用户查询自己id查询自己的挂号信息
     */
    @GetMapping("/searchInfoByUserId")
    public String searchInfoByUserId(int userId , Model model){
        System.out.println("1111111111111");
        return registrationService.searchDetailInfoByUserId(userId,model);
    }
    //预约
    @GetMapping("/yuYue")
    @ResponseBody    //返回json所用
    public R focus(HttpSession session , Integer dId,Model model){
        return registrationService.yuYue(session,dId,model);
    }
}
