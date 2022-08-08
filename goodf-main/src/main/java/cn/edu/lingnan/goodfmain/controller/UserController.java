package cn.edu.lingnan.goodf.controller;

import java.util.Arrays;
import java.util.Map;


import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.R;
import cn.edu.lingnan.goodf.entity.RegistrationEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.edu.lingnan.goodf.common.validator.group.ForgetAndUpdatePassword;
import cn.edu.lingnan.goodf.common.validator.group.Login;
import cn.edu.lingnan.goodf.common.validator.group.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.edu.lingnan.goodf.entity.UserEntity;
import cn.edu.lingnan.goodf.service.UserService;

import javax.servlet.http.HttpSession;


/**
 * 用户
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:55
 */
@RestController
@RequestMapping("goodf/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 登录检查手机号码
     */
    @PostMapping("/checkLoginTel")
    public R checkLoginTel(String tel){
        return userService.checkTel(tel);
    }

    /**
     * 登录检查密码是否为空
     */
    @PostMapping("/checkLoginPassword")
    public R checkLoginPassword(String password){
        return userService.checkLoginPassword(password);
    }

    /**
     * 登录检查验证码是否为空
     */
    @PostMapping("/checkLoginCode")
    public R checkLoginCode(String code){
        return userService.checkLoginCode(code);
    }

    //登录
    @PostMapping("/doLogin")
    public R doLogin(@Validated(Login.class) @RequestBody UserEntity userEntity, HttpSession session){
        return userService.doLogin(userEntity , session);
    }

    /**
     * 注册检查用户名是否为空
     */
    @PostMapping("/checkRegisterUsername")
    public R username(String username){
        return userService.checkRegisterUsername(username);
    }

    /**
     * 注册检查用户手机号码是否为空
     */
    @PostMapping("/checkRegisterTel")
    public R checkRegisterTel(String tel){
        return userService.checkRegisterTel(tel);
    }

    /**
     * 注册检查用户密码是否为空
     */
    @PostMapping("/checkRegisterPassword")
    public R checkRegisterPassword(String password){
        return userService.checkRegisterPassword(password);
    }

    /**
     * 注册检查用户确认密码是否为空
     */
    @PostMapping("/checkRegisterPassword1")
    public R checkRegisterPassword1(String password1){
        return userService.checkRegisterPassword1(password1);
    }

    /**
     * 注册检查用户地址是否为空
     */
    @PostMapping("/checkRegisterAddress")
    public R checkRegisterAddress(String address){
        return userService.checkRegisterAddress(address);
    }

    /**
     * 注册验证码是否为空
     */
    @PostMapping("/checkRegisterTelCode")
    public R checkRegisterTelCode(String telCode){
        return userService.checkRegisterTelCode(telCode);
    }

    /**
     * 注册
     */
    @PostMapping("/doRegister")
    public R doRegister(@Validated(Register.class) @RequestBody UserEntity userEntity, HttpSession session){
//        System.out.println(userEntity);
        return userService.doRegister(userEntity , session);
    }

    /**
//     * 忘记密码，修改密码
//     */
    @PostMapping("/forgetAndUpdatePassword")
    public R forgetAndUpdatePassword(@Validated(ForgetAndUpdatePassword.class)
                                         @RequestBody UserEntity userEntity , HttpSession session){
        return userService.forgetAndUpdatePassword(userEntity , session);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("goodf:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("goodf:user:info")
    public R info(@PathVariable("userId") Integer userId){
		UserEntity user = userService.getById(userId);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("goodf:user:save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("goodf:user:update")
    // @RequestBody 这里不要加上 这个注解 前端会报405错误 （参数格式错误
    public R update(UserEntity user,String newConPwd){

        return userService.update(user,newConPwd);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("goodf:user:delete")
    public R delete(@RequestBody Integer[] userIds){
		userService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }
    @RequestMapping("updateInfoByUserId")
    public String updateInfoByUserId(int userId , Model model) {
         UserEntity userEntity = userService.getById(userId);
         model.addAttribute("userEntity",userEntity);
        return "hospital/userUpdate";
    }



}
