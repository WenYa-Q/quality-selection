package com.wenya.service.impl;

import com.wenya.mapper.ProductSkuMapper;
import com.wenya.quality.doamin.product.ProductSku;
import com.wenya.quality.dto.h5.ProductSkuDto;
import com.wenya.quality.dto.product.ProductDto;
import com.wenya.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    /**
     * 按页面查找
     *
     * @param page          第页
     * @param limit         限制
     * @param productSkuDto 产品sku dto
     * @return {@link List }<{@link ProductSku }>
     */
    @Override
    public List<ProductSku> findByPage(int page, int limit, ProductSkuDto productSkuDto) {
        return productSkuMapper.findByPage(productSkuDto);
    }
}
