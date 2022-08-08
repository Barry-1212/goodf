package cn.edu.lingnan.goodf.dao;

import cn.edu.lingnan.goodf.entity.RegistrationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 挂号
 * 
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:55
 */
@Mapper
public interface RegistrationDao extends BaseMapper<RegistrationEntity> {

    // 驼峰命名只针对实体类有效！！！
    // 注意 封装 时间时 因为这里是HahsMap 所以要转换时间格式（因为HashMap的value 无法接收 数据库的Date格式
    // 根据 用户查出挂号表的信息（联合医生表 ---> 查出医生信息
    public ArrayList<HashMap> searchDetailInfoByUserId(int userId);
	
}
