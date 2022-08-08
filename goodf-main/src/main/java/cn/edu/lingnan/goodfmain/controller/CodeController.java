package cn.edu.lingnan.goodf.controller;

import cn.edu.lingnan.goodf.common.utils.CodeUtils;
import cn.edu.lingnan.goodf.common.utils.R;
//import com.aliyuncs.exceptions.ClientException;
import cn.edu.lingnan.goodf.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//返回code 登陆页面
@Controller
public class CodeController {

    @Autowired
    private CodeService codeService;

    //生成的code 是通过流回湿到登陆页面
    @GetMapping("/getCode")
    public void getCode(HttpServletRequest request , HttpServletResponse response){
        CodeUtils.getvalidateCode(request , response);
    }

    //发送手机验证码
    @PostMapping("/getTelCode")
    @ResponseBody
    public R getTelCode( String tel , HttpSession httpSession){
       return codeService.checkAndSend(tel , httpSession);
    }


}
