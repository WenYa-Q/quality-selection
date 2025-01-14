package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.product.ProductSpec;
import com.wenya.quality.mapper.ProductSpecMapper;
import com.wenya.quality.service.IProductSpecService;
import org.springframework.stereotype.Service;


/**
 * 产品规格服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class ProductSpecServiceImpl extends ServiceImpl<ProductSpecMapper, ProductSpec> implements IProductSpecService {
}
