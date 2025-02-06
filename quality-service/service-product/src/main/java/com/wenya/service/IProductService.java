package com.wenya.service;

import com.wenya.quality.doamin.product.ProductSku;
import com.wenya.quality.dto.h5.ProductSkuDto;
import com.wenya.quality.vo.h5.ProductItemVo;

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

    /**
     * 获取产品详细信息
     *
     * @param id id
     * @return {@link ProductItemVo }
     */
    ProductItemVo getProductDetails(Long id);

    /**
     * 通过sku id获取
     *
     * @param skuId sku id
     * @return {@link ProductSkuDto }
     */
    ProductSku getBySkuId(Long skuId);
}
