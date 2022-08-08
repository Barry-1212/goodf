package cn.edu.lingnan.goodf.vo;

import cn.edu.lingnan.goodf.entity.*;
import lombok.Data;

import java.util.List;


@Data
public class IndexVO {

    /**
     *封装多个List对象
     */

    //医生
    private List<DoctorEntity> doctorEntities;


    //area
    private List<AreaEntity> areaEntities;

    //科室分类
    private List<DepclassEntity> depclassEntities;

    // 科室
    private List<HospitalEntity> hospitalEntities;


}
