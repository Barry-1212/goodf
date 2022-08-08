package cn.edu.lingnan.goodf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 医生
 * 
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:56
 */
@Data
@TableName("doctor")
public class DoctorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 医生id
	 */
	@TableId(type = IdType.AUTO)
	private Integer dId;
	/**
	 * 医生姓名
	 */

	@JsonProperty("dName")
	private String dName;
	/**
	 * 医院id
	 */
	@JsonProperty("hId")
	private Integer hId;
	/**
	 * 医院姓名
	 */
	@JsonProperty("hName")
	private String hName;
	/**
	 * 科室分类id
	 */
	private Integer dcId;
	/**
	 * 科室分类名
	 */
	private String dcName;
	/**
	 * 科室id
	 */
	private Integer depId;
	/**
	 * 科室名
	 */
	private String depName;
	/**
	 * 挂号费
	 */
	private Double reMoney;
	/**
	 * 上班时间
	 */
	private Date startTime;
    /**
     * 下班时间
     */
    private Date endTime;
	/**
	 * 医生照片
	 */
	private String docPhoto;

	/**
	 * 医生简介
	 */
	private String docIntroduction;

}
