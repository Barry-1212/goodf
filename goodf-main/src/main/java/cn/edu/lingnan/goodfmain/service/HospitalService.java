package cn.edu.lingnan.goodf.service;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.lingnan.goodf.entity.HospitalEntity;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * 医院
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
public interface HospitalService extends IService<HospitalEntity> {

    PageUtils queryPage(Map<String, Object> params);



    //通过医院id获取医院详情信息
    public String GetHospitalByHId(Integer hId, Model model);

    /**
     * 查询所有医院
     * @return
     */
    List<HospitalEntity> selectAllHospital();
//    //通过医院id查询医生信息
    public String GetDocInfoByHId(Integer hId,Model model);

}

