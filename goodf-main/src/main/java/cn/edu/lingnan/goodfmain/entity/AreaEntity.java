package cn.edu.lingnan.goodf.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("area")
public class AreaEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId
    private Integer aId;

    private String area;
}
