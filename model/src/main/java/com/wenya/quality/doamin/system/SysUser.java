package com.wenya.quality.doamin.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wenya.quality.doamin.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户实体类
 *
 * @author wenya
 */
@Data
@Schema(description = "系统用户实体类")
@TableName("sys_user")
public class SysUser extends BaseEntity {

	@TableField("username")
	@Schema(description = "用户名")
	private String userName;

	@TableField("password")
	@Schema(description = "密码")
	private String password;

	@TableField("name")
	@Schema(description = "昵称")
	private String name;

	@TableField("phone")
	@Schema(description = "手机号码")
	private String phone;

	@TableField("avatar")
	@Schema(description = "图像")
	private String avatar;

	@TableField("description")
	@Schema(description = "描述")
	private String description;

	@TableField("status")
	@Schema(description = "状态（1：正常 0：停用）")
	private Integer status;

}