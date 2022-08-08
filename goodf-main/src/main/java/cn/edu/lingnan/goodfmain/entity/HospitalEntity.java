package cn.edu.lingnan.goodf.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 医院
 * 
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
@Data
@TableName("hospital")
public class HospitalEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 医院id
	 */
	@TableId
	private Integer hId;
	/**
	 * 医院姓名
	 */
	private String hName;
	/**
	 * 医院电话
	 */
	private String tel;
	/**
	 * 医院所属Id
	 */
	private Integer aId;
	/**
	 * 医院所属地区
	 */
	private String area;
	/**
	 * 医院的简介
	 */
	private String hBi;

}
