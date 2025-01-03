package com.wenya.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.product.Product;
import com.wenya.quality.dto.product.ProductDto;

import java.util.List;

/**
 * 产品服务
 * Description：
 *
 * @author wuqiulin
 */
public interface IProductService extends IService<Product> {
    /**
     * 按页面查找
     *
     * @return {@link List }<{@link ? }>
     */
    List<Product> findByPage(ProductDto productDto);
}
