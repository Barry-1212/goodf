package cn.edu.lingnan.goodf.service.impl;

import cn.edu.lingnan.goodf.common.utils.*;
import cn.edu.lingnan.goodf.dao.CodeDao;
import cn.edu.lingnan.goodf.entity.CodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.Query;
import cn.edu.lingnan.goodf.common.utils.R;
import org.springframework.stereotype.Service;


import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.edu.lingnan.goodf.dao.UserDao;
import cn.edu.lingnan.goodf.entity.UserEntity;
import cn.edu.lingnan.goodf.service.UserService;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Autowired(required = false)
    private CodeDao codeDao;


//    @Autowired(required = false)
//    private TelFilter telFilter;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }


    /**
     * 检查手机号码
     */
    @Override
    public R checkTel(String tel) {
        if ("".equals(tel) || tel == null)
            return R.error(201 , "用户电话号码不能为空");
        TelFilter telFilter = new TelFilter();
        if ( !(telFilter.isMobile(tel) || telFilter.isPhone(tel))){
            return R.error(202 , "用户电话号码格式不对");
        }
        return R.ok("");
    }

    /**
     * 登录检查密码是否为空
     */
    @Override
    public R checkLoginPassword(String password) {
        if ("".equals(password) || password == null)
            return R.error(201 , "密码不能为空");
        return R.ok("");
    }

    @Override
    public R checkLoginCode(String code) {
        if ("".equals(code) || code == null)
            return R.error(201 , "验证码不能为空");
        return R.ok("");
    }

    @Override
    public R doLogin(UserEntity userEntity  , HttpSession session) {
        checkTel(userEntity.getTel());
        String loginCode = (String)session.getAttribute("loginCode");
        if (loginCode == null || "".equals(loginCode))
            return R.error(201, "验证码已失效");
        if (!loginCode.equalsIgnoreCase(userEntity.getCode())) {
            return R.error(202, "验证码不正确");
        }
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("tel" , userEntity.getTel());
        UserEntity userEntity1 = userDao.selectOne(queryWrapper);
        if (userEntity1 == null)
            return R.error(203 , "该手机号码还没有注册，请先注册");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(userEntity.getPassword() , userEntity1.getPassword()))
            return R.error(204 , "登录失败，请检查登录信息");
        session.setAttribute("user" , userEntity1);
        return R.ok("登录成功");
    }

    @Override
    public R checkRegisterUsername(String username) {
        if ("".equals(username) || username == null)
            return R.error(201 , "用户名不能为空");
        return R.ok("");
    }

    @Override
    public R checkRegisterAddress(String address) {
        if ("".equals(address) || address == null)
            return R.error(201 , "地址不能为空");
        return R.ok("");
    }

    @Override
    public R checkRegisterPassword(String password) {
        if ("".equals(password) || password == null)
            return R.error(201 , "密码不能为空");
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")){
            return R.error("8个字符，至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符（非数字字母）");
        }
        return R.ok("");
    }

    @Override
    public R checkRegisterPassword1(String password1) {
        if ("".equals(password1) || password1 == null)
            return R.error(201 , "确认密码不能为空");
        return R.ok("");
    }

    @Override
    public R checkRegisterTel(String tel) {
        if ("".equals(tel) || tel == null)
            return R.error(201 , "用户电话号码不能为空");
        TelFilter telFilter = new TelFilter();
        if ( !(telFilter.isMobile(tel) || telFilter.isPhone(tel))){
            return R.error(202 , "用户电话号码格式不对");
        }
        return R.ok("");
    }

    @Override
    public R checkRegisterTelCode(String telCode) {
        if ("".equals(telCode) || telCode == null)
            return R.error(201 , "验证码不能为空");
        return R.ok("");
    }

    @Override
    public R doRegister(UserEntity userEntity, HttpSession session) {
        checkTel(userEntity.getTel());
        if (!userEntity.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")){
            return R.error(207  , "8个字符，至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符（非数字字母）");
        }
        if (!userEntity.getPassword().equals(userEntity.getPassword1()))
            return R.error(203 , "确认密码不正确");
        String telCode = (String)session.getAttribute("telCode");
        if ("".equals(telCode) || telCode ==null)
            return R.error(206 , "验证码失效");
        if (!telCode.equals(userEntity.getTelCode())) {
            return R.error(204, "验证码不正确，请重新获取验证码");
        }
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel" , userEntity.getTel());
        UserEntity userEntity1 = userDao.selectOne(queryWrapper);
        if ( userEntity1 != null)
            return R.error(205 , "该手机号码已注册，请登录");

        //密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodePassword);
        userEntity.setSuperuser(1);
        int insert = userDao.insert(userEntity);
        if (insert != 1)
            return R.error(206 , "注册失败");
        codeDao.delete(new QueryWrapper<CodeEntity>().eq("tel" , userEntity.getTel()));
        return R.ok("注册成功, 请登录");
    }

    @Override
    public R insertGiteeId(String giteeId , HttpSession session) {
        UserEntity userEntity = this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("gitee_id", giteeId));
        if (userEntity == null) {
            UserEntity userEntity1 = new UserEntity();
            userEntity1.setSuperuser(1);
            userEntity1.setPassword("×××");
            userEntity1.setUserName("×××");
            userEntity1.setGiteeId(giteeId);
            int insert = this.baseMapper.insert(userEntity1);
            if (insert ==0)
                return R.error(201 , "201");
            UserEntity userEntity2 = this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("gitee_id", giteeId));
            session.setAttribute("user" , userEntity2);
            return R.error(202 , "个人信息没有完善，请到个人页面完善个人信息");
        }
        if ("".equals(userEntity.getTel()) || userEntity.getTel() ==null ) {
            session.setAttribute("user" , userEntity);
            return R.error(203, "手机还没有绑定，请到个人页面完绑定");
        }
        session.setAttribute("user" , userEntity);

        return R.ok("");
    }

    @Override
    public R forgetAndUpdatePassword(UserEntity userEntity , HttpSession session) {
        UserEntity userEntity1 = this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("tel", userEntity.getTel()));
        if (userEntity1 == null)
            return R.error(201 , "该账号不存在");
        if (!userEntity.getPassword().equals(userEntity.getPassword1()))
            return R.error(202 , "确认密码不正确");
        String telCode = (String)session.getAttribute("telCode");
        if ("".equals(telCode) || telCode ==null)
            return R.error(206 , "验证码失效");
        if (!telCode.equals(userEntity.getTelCode())) {
            return R.error(204, "验证码不正确，请重新获取验证码");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(userEntity.getPassword());
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setPassword(encode);
        int tel = this.baseMapper.update(userEntity2, new QueryWrapper<UserEntity>().eq("tel", userEntity1.getTel()));
        if (tel == 0)
            return R.error(203 , "修改密码失败");
        codeDao.delete(new QueryWrapper<CodeEntity>().eq("tel" , userEntity.getTel()));
        return R.ok("修改密码成功");
    }

    @Override
    public String updateUserById(UserEntity user, Model model) {
        int i = userDao.updateById(user);
        if (i == 0){
            model.addAttribute("msg","更新失败");
//            return "/";
        }
        model.addAttribute("user",user);
        model.addAttribute("msg","更新成功");
        return "hospital/gerenzhongxin";

    }

    // 重写update方法
    @Override
    public R update(UserEntity user,String newConPwd) {
        R r = new R();
        if (StringUtils.isEmpty(user.getUserName())){
            return R.error("姓名不能为空");
        }
        String password = user.getPassword();
        System.out.println(password);
        System.out.println(newConPwd);
        // 至少8个字符，至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符（非数字字母）：
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")){
            return R.error("8个字符，至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符（非数字字母）");
        }
        if(StringUtils.isEmpty(newConPwd)){
            return R.error("确认密码不能为空");
        }
        if(!password.equals(newConPwd)){
            return R.error("两次输入的密码不一致");
        }
        if (StringUtils.isEmpty(user.getAddress())){
            return R.error("地址不能为空");
        }
        if (StringUtils.isEmpty(user.getTel())){
            return R.error("电话不能为空");
        }
        if (!user.getTel().matches("^[1][3,4,5,7,8][0-9]{9}$")){
            return R.error("手机号码格式错误");
        }
        // 查找手机是否已经被注册
        // 根据id查出原来的电话号码
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper();
        UserEntity userEntity1 = userDao.selectOne(queryWrapper.eq("user_id",user.getUserId()));
        String phone = userEntity1.getTel();

        // 根据电话号码查出实体类
        UserEntity phoneEntity = userDao.selectOne(new QueryWrapper<UserEntity>().eq("tel",user.getTel()).ne("tel",phone));
        //
        if (phoneEntity != null ){
            return R.error("该电话已经注册，请重新填入号码");
        }
        // 更改属性
        UserEntity userEntity = userDao.selectById(user.getUserId());
        userEntity.setUserName(user.getUserName());
        //密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodePassword);
        userEntity.setAddress(user.getAddress());
        userEntity.setTel(user.getTel());
        // 数据库操作
        userDao.updateById(userEntity);
        // 封装返回的数据
        r.put("userEntity",userEntity);
        r.put("msg","修改成功");
        return r;
    }


}