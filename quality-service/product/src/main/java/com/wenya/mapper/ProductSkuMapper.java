package com.wenya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.product.ProductSku;
import com.wenya.quality.dto.h5.ProductSkuDto;
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

    /**
     * 按页面查找
     *
     * @param productSkuDto 产品sku dto
     * @return {@link List }<{@link ProductSku }>
     */
    List<ProductSku> findByPage(ProductSkuDto productSkuDto);
}
