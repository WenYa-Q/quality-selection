package com.wenya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 产品sku映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface ProductSkuMapper extends BaseMapper<ProductSku> {
    /**
     * 通过类别查找产品sku
     *
     * @return {@link List }<{@link ProductSku }>
     */
    List<ProductSku> findProductSkuBySale();
}
