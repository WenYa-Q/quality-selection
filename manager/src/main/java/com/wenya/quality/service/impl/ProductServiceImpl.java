package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.product.Product;
import com.wenya.quality.dto.product.ProductDto;
import com.wenya.quality.mapper.ProductMapper;
import com.wenya.quality.service.IProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    private ProductMapper productMapper;

    /**
     * 按页面查找
     *
     * @return {@link List }<{@link Product }>
     */
    @Override
    public List<Product> findByPage(ProductDto productDto) {
        return productMapper.findByPage(productDto);
    }
}
