package com.wenya.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.mapper.ProductSkuMapper;
import com.wenya.quality.doamin.product.ProductSku;
import com.wenya.service.IProductSkuService;
import org.springframework.stereotype.Service;

/**
 * 产品sku服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements IProductSkuService {
}
