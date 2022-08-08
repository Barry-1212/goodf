package cn.edu.lingnan.goodf.service.impl;

import cn.edu.lingnan.goodf.common.utils.PageUtils;
import cn.edu.lingnan.goodf.common.utils.Query;
import cn.edu.lingnan.goodf.entity.DeparmentEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.edu.lingnan.goodf.dao.DepclassDao;
import cn.edu.lingnan.goodf.entity.DepclassEntity;
import cn.edu.lingnan.goodf.service.DepclassService;


@Service("depclassService")
public class DepclassServiceImpl extends ServiceImpl<DepclassDao, DepclassEntity> implements DepclassService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DepclassEntity> page = this.page(
                new Query<DepclassEntity>().getPage(params),
                new QueryWrapper<DepclassEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<DepclassEntity> selectDepclass() {
        List<DepclassEntity> list = this.list(new QueryWrapper<DepclassEntity>());
        return list;
    }



}