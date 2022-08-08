package cn.edu.lingnan.goodf.service;

import cn.edu.lingnan.goodf.common.utils.R;
import cn.edu.lingnan.goodf.entity.CodeEntity;
import cn.edu.lingnan.goodf.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;


/**
 * 验证码
 */
public interface CodeService extends IService<CodeEntity> {


    R checkAndSend(String tel , HttpSession httpSession);
}
