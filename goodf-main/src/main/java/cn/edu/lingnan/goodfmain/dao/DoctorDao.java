package cn.edu.lingnan.goodf.dao;

import cn.edu.lingnan.goodf.entity.DoctorEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 医生
 * 
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
@Mapper
public interface DoctorDao extends BaseMapper<DoctorEntity> {

    //查询所有hIds医院包含的所有医生
    List<DoctorEntity> getDoctorsByHid(@Param("hIds") List<Integer> hIds);


}
