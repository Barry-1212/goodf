package cn.edu.lingnan.goodf.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 科室分类
 * 
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
@Data
@TableName("depclass")
public class DepclassEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 科室分类表ID
	 */
	@TableId
	private Integer dcId;
	/**
	 * 科室分类名
	 */
	private String dcName;

}
