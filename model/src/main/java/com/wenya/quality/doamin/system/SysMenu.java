package com.wenya.quality.doamin.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wenya.quality.doamin.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * sys菜单
 * Description：
 *
 * @author wuqiulin
 */
@TableName("sys_menu")
@Schema(description = "系统菜单实体类")
@Data
public class SysMenu extends BaseEntity {

	@TableField(value = "parent_id")
	@Schema(description = "父节点id")
	private Long parentId;

	@TableField(value = "title")
	@Schema(description = "节点标题")
	private String title;

	@TableField(value = "component")
	@Schema(description = "组件名称")
	private String component;

	@TableField(value = "sort_value")
	@Schema(description = "排序值")
	private Integer sortValue;

	@TableField(value = "status")
	@Schema(description = "状态(0:禁止,1:正常)")
	private Integer status;

	// 下级列表
	@TableField(exist = false)
	@Schema(description = "子节点")
	private List<SysMenu> children;

}