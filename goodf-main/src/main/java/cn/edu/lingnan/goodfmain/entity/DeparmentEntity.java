package cn.edu.lingnan.goodf.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 科室
 * 
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
@Data
@TableName("deparment")
public class DeparmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 科室表ID
	 */
	@TableId
	private Integer depId;
	/**
	 * 科室表名称
	 */
	private String depName;
	/**
	 * 所属科室分类id
	 */
	private Integer dcId;

}
