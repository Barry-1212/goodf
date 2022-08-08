package cn.edu.lingnan.goodf.web;

import cn.edu.lingnan.goodf.entity.UserEntity;
import cn.edu.lingnan.goodf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserControllerWeb {

    @Autowired
    private UserService userService;

    @RequestMapping("/updateInfoByUserId")
    public String updateInfoByUserId(int userId , Model model) {
        UserEntity userEntity = userService.getById(userId);
        model.addAttribute("userEntity",userEntity);
        return "hospital/userUpdate";
    }
}
