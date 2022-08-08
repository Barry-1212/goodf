package cn.edu.lingnan.goodf.service;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.lingnan.goodf.entity.UserEntity;
import org.springframework.ui.Model;


import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用户
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:55
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R checkTel(String tel);

    R checkLoginPassword(String password);

    R checkLoginCode(String code);


    R doLogin(UserEntity userEntity , HttpSession session);

    R checkRegisterUsername(String username);

    R checkRegisterAddress(String address);

    R checkRegisterPassword(String password);

    R checkRegisterPassword1(String password1);

    R checkRegisterTel(String tel);

    R checkRegisterTelCode(String telCode);

    R doRegister(UserEntity userEntity, HttpSession session);

    R insertGiteeId(String giteeID , HttpSession session);

    R forgetAndUpdatePassword(UserEntity userEntity , HttpSession session);







    String updateUserById(UserEntity user, Model model);


    R update(UserEntity user ,String newConPwd);
}

