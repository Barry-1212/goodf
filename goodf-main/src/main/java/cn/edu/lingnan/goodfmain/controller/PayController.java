package cn.edu.lingnan.goodf.controller;

import cn.edu.lingnan.goodf.entity.RegistrationEntity;
import cn.edu.lingnan.goodf.entity.UserEntity;
import cn.edu.lingnan.goodf.service.PayService;
import cn.edu.lingnan.goodf.service.RegistrationService;
import cn.edu.lingnan.goodf.vo.PayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class PayController {

    @Autowired(required = false)
    PayService payService;

    @RequestMapping("/pay")
    public String pay(String numId )throws Exception{
//        System.out.println("nimId"+numId);
        return payService.pay(numId);
    }
    @PostMapping("/handlePayResult")
    public String handlePayResult(PayVo vo){
        payService.handlePayResult(vo);
        return "success";
    }

    @GetMapping("/returnInfo")
    public  String returnInfo(HttpSession session, HttpServletResponse response ) throws IOException {
        UserEntity user = (UserEntity) session.getAttribute("user");
//        payService.handlePayResult(vo);
        response.sendRedirect("http://localhost:10000/searchInfoByUserId?userId="+user.getUserId());
        return null;
    }

}
