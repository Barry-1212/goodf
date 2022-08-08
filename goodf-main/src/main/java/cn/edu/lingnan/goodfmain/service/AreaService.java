package cn.edu.lingnan.goodf.service;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.entity.AreaEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.ui.Model;


import java.util.List;
import java.util.Map;

public interface AreaService extends IService<AreaEntity> {
    PageUtils queryPage(Map<String, Object> params);

    //通过首页的找医院，点击不同的地址，获取该地址的id，随后通过该id查询所属地所有的医院
    //并且查询所有的地区一起传送到下一个页面继续使用数据
    public String GetHospitalByAreaIdAndGetAllArea(String area, Model model);

    //用户登录成功后跳转到index获取所有地区
    List<AreaEntity> selectAreaAll();





}
