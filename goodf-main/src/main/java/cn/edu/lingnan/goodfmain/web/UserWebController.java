package cn.edu.lingnan.goodf.web;

import cn.edu.lingnan.goodf.common.utils.LoginGitee;
import cn.edu.lingnan.goodf.common.utils.R;
import cn.edu.lingnan.goodf.service.DoctorService;
import cn.edu.lingnan.goodf.service.UserService;
import cn.edu.lingnan.goodf.vo.IndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("web")
public class UserWebController {


    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    /**
     * 打开登录页面
     * 第一个页面
     * yyh
     * @return
     */
    @GetMapping("/toLogin")
    public String login(){
        return "user/login";
    }

    /**
     * 跳转到注册页面
     * yyh
     */
    @GetMapping("/toRegister")
    public String toRegister(){
        return "user/register";
    }


    /**
     * 用户登录成功跳转到首页
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model ){
        model.addAttribute("indexVo" , doctorService.getIndexVo());
        return "hospital/index";
    }

    /**
     * 到gitee第三方登录授权
     */
    @GetMapping("/toLoginGitee")
    public String toLoginGitee(){
        return "redirect:https://gitee.com/oauth/authorize?" +
                "client_id=212d67dbcdbc6ce1c7cee277ade5c50df6d206605a4985a564c2894eae92b23b" +
                "&redirect_uri=http://localhost:10000/web/loginGitee&response_type=code";
    }

    /**
     * 获取到gitee确认授权登录后使用得到的code获取access_token
     * 根据获得到的access_token从gitee获取授权的用户信息，
     * 将用户信息的gitee的id和基本信息存进user表
     * @param model
     * @param code
     * @param session
     * @return
     */
    @GetMapping("/loginGitee")
    public String loginGitee(Model model , String code , HttpSession session){
        LoginGitee loginGitee = new LoginGitee();
        String giteeId = loginGitee.sendPostGetAccessToken(code);
        if ("".equals(giteeId) || giteeId ==null)
            return  "user/login";
        R r = userService.insertGiteeId(giteeId , session);
        if (r.get("code").equals("201"))
            return  "user/login";
        model.addAttribute("indexVo" , doctorService.getIndexVo());
        return "hospital/index";
    }

    @GetMapping("/toUpdatePassword")
    public String toUpdatPassword(){
        return "user/updatePassword";
    }






}
