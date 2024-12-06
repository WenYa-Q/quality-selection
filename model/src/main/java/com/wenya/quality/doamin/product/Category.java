package com.wenya.quality.doamin.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wenya.quality.doamin.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "分类实体类")
@TableName("category")
public class Category extends BaseEntity {

	@Schema(description = "分类名称")
	@TableField("name")
	private String name;

	@Schema(description = "分类图片url")
	@TableField("image_url")
	private String imageUrl;

	@Schema(description = "父节点id")
	@TableField("parent_id")
	private Long parentId;

	@Schema(description = "分类状态: 是否显示[0-不显示，1显示]")
	@TableField("status")
	private Integer status;

	@Schema(description = "排序字段")
	@TableField("order_num")
	private Integer orderNum;

	@Schema(description = "是否存在子节点")
	@TableField(exist = false)
	private Boolean hasChildren;

	@Schema(description = "子节点List集合")
	@TableField(exist = false)
	private List<Category> children;

}