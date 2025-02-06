package com.wenya.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wenya.mapper.ProductDetailsMapper;
import com.wenya.mapper.ProductMapper;
import com.wenya.mapper.ProductSkuMapper;
import com.wenya.quality.doamin.product.Product;
import com.wenya.quality.doamin.product.ProductDetails;
import com.wenya.quality.doamin.product.ProductSku;
import com.wenya.quality.dto.h5.ProductSkuDto;
import com.wenya.quality.vo.h5.ProductItemVo;
import com.wenya.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

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

    /**
     * 获取产品详细信息
     *
     * @param id id
     * @return {@link ProductItemVo }
     */
    @Override
    public ProductItemVo getProductDetails(Long id) {
        //获取商品的sku信息
        ProductSku productSku = productSkuMapper.selectById(id);

        //获取商品的基本信息
        Product product = productMapper.selectById(productSku.getProductId());

        //同一个商品下面的sku信息列表
        List<ProductSku> productSkuList = productSkuMapper.selectList(new LambdaQueryWrapper<ProductSku>()
                .eq(ProductSku::getProductId, product.getId()));

        //建立sku规格与skuId对应关系
        Map<String,Object> skuSpecValueMap = new HashMap<>();
        productSkuList.forEach(sku -> {
            skuSpecValueMap.put(sku.getSkuSpec(),sku.getId());
        });

        //商品详情信息
        ProductDetails productDetails = productDetailsMapper.selectOne(new LambdaQueryWrapper<ProductDetails>()
                .eq(ProductDetails::getProductId, product.getId()));

        //返回数据
        ProductItemVo productItemVo = new ProductItemVo();
        productItemVo.setProduct(product);
        productItemVo.setProductSku(productSku);
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));

        return productItemVo;
    }

    /**
     * 通过sku id获取
     *
     * @param skuId sku id
     * @return {@link ProductSku }
     */
    @Override
    public ProductSku getBySkuId(Long skuId) {
        return productSkuMapper.selectById(skuId);
    }
}
