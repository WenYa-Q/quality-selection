package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.product.Product;
import com.wenya.quality.doamin.product.ProductDetails;
import com.wenya.quality.doamin.product.ProductSku;
import com.wenya.quality.dto.product.ProductDto;
import com.wenya.quality.mapper.ProductDetailsMapper;
import com.wenya.quality.mapper.ProductMapper;
import com.wenya.quality.mapper.ProductSkuMapper;
import com.wenya.quality.service.IProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private ProductSkuMapper productSkuMapper;

    @Resource
    private ProductDetailsMapper productDetailsMapper;

    /**
     * 按页面查找
     *
     * @return {@link List }<{@link Product }>
     */
    @Override
    public List<Product> findByPage(ProductDto productDto) {
        return productMapper.findByPage(productDto);
    }

    /**
     * 保存产品
     *
     * @param product 产品
     * @return int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveProduct(Product product) {
        //设置商品状态
        product.setStatus(0);
        product.setAuditStatus(0);
        //保存
        productMapper.insert(product);

        //保存商品SKU数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        for (int i = 0; i < productSkuList.size(); i++) {
            ProductSku productSku = productSkuList.get(i);

            productSku.setSkuCode(product.getId() + "_" + i);
            productSku.setProductId(product.getId());
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            productSku.setStockNum(0);
            productSku.setStatus(0);

            productSkuMapper.insert(productSku);
        }

        //构建商品详情
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());

        return productDetailsMapper.insert(productDetails);
    }
}
