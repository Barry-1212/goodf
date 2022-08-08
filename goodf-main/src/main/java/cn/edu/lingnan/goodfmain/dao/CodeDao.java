package cn.edu.lingnan.goodf.dao;

import cn.edu.lingnan.goodf.entity.CodeEntity;
import cn.edu.lingnan.goodf.entity.UserEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 验证码
 */
@Mapper
public interface CodeDao extends BaseMapper<CodeEntity> {

}
