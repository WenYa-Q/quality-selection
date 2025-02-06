package com.wenya.product;

import com.wenya.quality.doamin.product.ProductSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 产品feign客户端
 * Description：
 *
 * @author wuqiulin
 */
@FeignClient("service-product")
public interface ProductFeignClient {

    @GetMapping("/api/product/getBySkuId/{skuId}")
    ProductSku getBySkuId(@PathVariable Long skuId) ;
}
