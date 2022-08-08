package cn.edu.lingnan.goodf.dao;

import cn.edu.lingnan.goodf.entity.UserEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * 
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:55
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {


}
