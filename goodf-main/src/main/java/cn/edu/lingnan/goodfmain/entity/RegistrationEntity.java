package cn.edu.lingnan.goodf.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 挂号
 * 
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:55
 */
@Data
@TableName("registration")
@Accessors(chain = true)
public class RegistrationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 挂号表id
	 */
	@TableId
	private Integer numId;
	/**
	 * 用户名
	 */
	private String userName;

	/*
	 用户id
	 */
	private Integer userId;

	/**
	 * 医院id
	 */
	private Integer hId;
	/**
	 * 医院名
	 */
	private String hName;
	/**
	 * 医生id
	 */
	private Integer dId;
	/**
	 * 医生名称
	 */
	private String dName;
	/**
	 * 科室表id
	 */
	private Integer depId;
	/**
	 * 科室表名
	 */
	private String depName;
	/**
	 * 科室分类id
	 */
	private Integer dcId;
	/**
	 * 科室分类名
	 */
	private String dcName;
	/**
	 * 支付时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;
	/**
	 * 支付状态
	 */
	private String paymentStatus;
	/**
	 * 就诊状态
	 */
	private String visitStatus;

}
