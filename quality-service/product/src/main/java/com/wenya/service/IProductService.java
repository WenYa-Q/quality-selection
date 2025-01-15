package com.wenya.service;

import com.wenya.quality.doamin.product.ProductSku;
import com.wenya.quality.dto.h5.ProductSkuDto;

import java.util.List;

/**
 * 产品服务
 * Description：
 *
 * @author wuqiulin
 */
public interface IProductService {
    /**
     * 按页面查找
     *
     * @param page          第页
     * @param limit         限制
     * @param productSkuDto 产品sku dto
     * @return {@link String }
     */
    List<ProductSku> findByPage(int page, int limit, ProductSkuDto productSkuDto);
}
