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


    /**
     * 保存产品
     *
     * @param product 产品
     * @return int
     */
    int saveProduct(Product product);

    /**
     * 按id获取产品
     *
     * @param id id
     * @return {@link Product }
     */
    Product getProductById(Long id);
}
