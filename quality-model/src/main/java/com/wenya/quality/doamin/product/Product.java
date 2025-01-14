package com.wenya.quality.doamin.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wenya.quality.doamin.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 产品
 * Description：
 *
 * @author wuqiulin
 */
@Data
@Schema(description = "商品实体类")
public class Product extends BaseEntity {

	/**
	 * 商品名称
	 */
	@Schema(description = "商品名称")
	private String name;

	/**
	 * 品牌id
	 */
	@Schema(description = "品牌id")
	private Long brandId;

	/**
	 * 一级分类id
	 */
	@Schema(description = "一级分类id")
	private Long category1Id;

	/**
	 * 二级分类id
	 */
	@Schema(description = "二级分类id")
	private Long category2Id;

	/**
	 * 三级分类id
	 */
	@Schema(description = "三级分类id")
	private Long category3Id;

	/**
	 * 计量单位
	 */
	@Schema(description = "计量单位")
	private String unitName;

	/**
	 * 轮播图url
	 */
	@Schema(description = "轮播图url")
	private String sliderUrls;

	/**
	 * 商品规格值json串
	 */
	@Schema(description = "商品规格值json串")
	private String specValue;

	/**
	 * 状态
	 */
	@Schema(description = "线上状态：0-初始值，1-上架，-1-自主下架")
	private Integer status;

	/**
	 * 审核状态
	 */
	@Schema(description = "审核状态")
	private Integer auditStatus;

	/**
	 * 审核信息
	 */
	@Schema(description = "审核信息")
	private String auditMessage;

	//扩展的属性，用来封装响应的数据

	/**
	 * 品牌名称
	 */
	@TableField(exist = false)
	@Schema(description = "品牌名称")
	private String brandName;

	/**
	 * 一级分类
	 */
	@TableField(exist = false)
	@Schema(description = "一级分类名称")
	private String category1Name;

	/**
	 * 二级分类
	 */
	@TableField(exist = false)
	@Schema(description = "二级分类名称")
	private String category2Name;

	/**
	 * 三级分类
	 */
	@TableField(exist = false)
	@Schema(description = "三级分类名称")
	private String category3Name;

	/**
	 * 产品sku列表
	 */
	@TableField(exist = false)
	@Schema(description = "sku列表集合")
	private List<ProductSku> productSkuList;

	/**
	 * 详细图像url
	 */
	@TableField(exist = false)
	@Schema(description = "图片详情列表")
	private String detailsImageUrls;

}