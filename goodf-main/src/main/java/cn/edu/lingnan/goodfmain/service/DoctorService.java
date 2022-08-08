package cn.edu.lingnan.goodf.service;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.vo.IndexVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.lingnan.goodf.entity.DoctorEntity;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 医生
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
public interface DoctorService extends IService<DoctorEntity> {

    PageUtils queryPage(Map<String, Object> params);


    //用户登录成功后获取所有医生、地区、科室分类和科室
    IndexVO getIndexVo();



    //根据首页的did查询该医生的详细情况
    String SelectDoctorDetailByDId(Integer dId , Model model);


    //
    String toYuYue(HttpSession session , Integer dId , Model model);

    /*
    预约操作，本该写在用户里面的？想着尽量别搞混别的界面，错了也好改正，所以就写来这里
    预约，需要uid与did，然后插入挂号表？
     */




    //根据地区id查询所有医生、地区、科室分类和科室
    IndexVO getDoctorsByAreaId(Integer areaId);


    //根据医院id查询该医院所有的医生
    IndexVO getDoctorsByHid(Integer hId);

    //获取该外科所有医生
    IndexVO getDoctorsByDcId(Integer dcId);


}

