package cn.edu.lingnan.goodf.service.impl;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.edu.lingnan.goodf.dao.DeparmentDao;
import cn.edu.lingnan.goodf.entity.DeparmentEntity;
import cn.edu.lingnan.goodf.service.DeparmentService;


@Service("deparmentService")
public class DeparmentServiceImpl extends ServiceImpl<DeparmentDao, DeparmentEntity> implements DeparmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DeparmentEntity> page = this.page(
                new Query<DeparmentEntity>().getPage(params),
                new QueryWrapper<DeparmentEntity>()
        );
        return new PageUtils(page);
    }

}