package cn.edu.lingnan.goodf.service;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.lingnan.goodf.entity.DepclassEntity;

import java.util.List;
import java.util.Map;

/**
 * 科室分类
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
public interface DepclassService extends IService<DepclassEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //查找所有科室分类
    List<DepclassEntity> selectDepclass();

}

