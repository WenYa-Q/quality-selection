package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品sku映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface ProductSkuMapper extends BaseMapper<ProductSku> {
}
