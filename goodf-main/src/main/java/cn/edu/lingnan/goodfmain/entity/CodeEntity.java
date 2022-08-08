package cn.edu.lingnan.goodf.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("code")
public class CodeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 验证码id
     */
    @TableId
    private Integer id;

    /**
     * 手机
     */
    private String tel;

    /**
     * 创建时间
     */
    private Date createTime;
}
