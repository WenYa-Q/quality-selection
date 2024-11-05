package com.wenya.quality.doamin.product;

import com.wenya.quality.doamin.base.BaseEntity;
import lombok.Data;

@Data
public class ProductDetails extends BaseEntity {

	private Long productId;
	private String imageUrls;

}