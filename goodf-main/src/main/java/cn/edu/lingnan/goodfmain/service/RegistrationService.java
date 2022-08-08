package cn.edu.lingnan.goodf.service;

import cn.edu.lingnan.goodf.entity.AreaEntity;
import cn.edu.lingnan.goodf.entity.RegistrationEntity;
import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.lingnan.goodf.entity.RegistrationEntity;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 挂号
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:55
 */
public interface RegistrationService extends IService<RegistrationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    // 用户根据自己的id查询挂号信息
    String searchInfoByUserId(int userId, Model model);


    //预约操作
    R yuYue(HttpSession session , Integer dId , Model model);

    // 用户根据自己的id查询挂号信息2.0（查询出医生上班时间
    String searchDetailInfoByUserId(int userId, Model model);
}

