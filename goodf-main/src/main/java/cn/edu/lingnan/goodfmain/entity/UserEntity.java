package cn.edu.lingnan.goodf.entity;

import cn.edu.lingnan.goodf.common.validator.group.ForgetAndUpdatePassword;
import cn.edu.lingnan.goodf.common.validator.group.Login;
import cn.edu.lingnan.goodf.common.validator.group.Register;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 用户
 *
 * @author liruimin
 * @email 1975377356@qq.com
 * @date 2022-06-24 09:47:55
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Integer userId;

	/**
	 * gitee第三方授权登录id
	 */
	private String giteeId;

	/**
	 * 使用NotBlank分组检验controller层的方式使用@NotBlank注解传进的参数是否为空
	 * 如果有空在GlobalExceptionControllerAdvice捕获异常将提示信息返回页面
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空" , groups = {Register.class})
	private String userName;

	/**
	 * 使用NotBlank分组检验controller层的方式使用@NotBlank注解传进的参数是否为空
	 * 如果有空在GlobalExceptionControllerAdvice捕获异常将提示信息返回页面
	 * 密码
	 */
	@NotBlank(message = "密码不能为空" , groups = {Login.class})
	@NotBlank(message = "密码不能为空" , groups = {Register.class})
	@NotBlank(message = "密码不能为空" , groups = {ForgetAndUpdatePassword.class})
	private String password;

	/**
	 * 使用NotBlank分组检验controller层的方式使用@NotBlank注解传进的参数是否为空
	 * 如果有空在GlobalExceptionControllerAdvice捕获异常将提示信息返回页面
	 *用户地址
	 */
	@NotBlank(message = "地址不能为空" , groups = {Register.class})
	private String address;

	/**
	 * 使用NotBlank分组检验controller层的方式使用@NotBlank注解传进的参数是否为空
	 * 如果有空在GlobalExceptionControllerAdvice捕获异常将提示信息返回页面
	 * 用户手机号码
	 */
	@NotBlank(message = "用户手机号码不能为空", groups = {Login.class})
	@NotBlank(message = "用户手机号码不能为空" , groups = {Register.class})
	@NotBlank(message = "用户手机号码不能为空" , groups = {ForgetAndUpdatePassword.class})
	private String tel;
	/**
	 * 用户权限
	 */
	private Integer superuser;
	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 使用NotBlank分组检验controller层的方式使用@NotBlank注解传进的参数是否为空
	 * 如果有空在GlobalExceptionControllerAdvice捕获异常将提示信息返回页面
	 * 确认密码
	 */
	@TableField(exist=false)
	@NotBlank(message = "确认密码不能为空" , groups = {Register.class})
	@NotBlank(message = "确认密码不能为空" , groups = {ForgetAndUpdatePassword.class})
	private String password1;

	/**
	 * 使用NotBlank分组检验controller层的方式使用@NotBlank注解传进的参数是否为空
	 * 如果有空在GlobalExceptionControllerAdvice捕获异常将提示信息返回页面
	 * 登录验证码
	 */
	@TableField(exist=false)
	@NotBlank(message = "验证码不能为空", groups = {Login.class})
	private String code;

	/**
	 * 使用NotBlank分组检验controller层的方式使用@NotBlank注解传进的参数是否为空
	 * 如果有空在GlobalExceptionControllerAdvice捕获异常将提示信息返回页面
	 * 手机验证码
	 */
	@TableField(exist=false)
	@NotBlank(message = "验证码不能为空" , groups = {Register.class})
	@NotBlank(message = "验证码不能为空" , groups = {ForgetAndUpdatePassword.class})
	private String telCode;



}
