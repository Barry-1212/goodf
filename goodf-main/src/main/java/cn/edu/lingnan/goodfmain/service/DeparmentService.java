package cn.edu.lingnan.goodf.service;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.lingnan.goodf.entity.DeparmentEntity;

import java.util.Map;

/**
 * 科室
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
public interface DeparmentService extends IService<DeparmentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

