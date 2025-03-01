package com.wenya.controller;

import com.wenya.quality.doamin.product.ProductSku;
import com.wenya.quality.dto.h5.ProductSkuDto;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品控制器
 * Description：
 *
 * @author wuqiulin
 */
@Tag(name = "产品", description = "产品控制器")
@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    /**
     * 按页面查找
     *
     * @param page  第页
     * @param limit 限制
     * @return {@link AjaxResult }
     */
    @Operation(summary = "按页面查找")
    @GetMapping("/{page}/{limit}")
    public AjaxResult findByPage(@PathVariable("page") int page, @PathVariable("limit") int limit, ProductSkuDto productSkuDto) {
        return AjaxResult.success(productService.findByPage(page, limit, productSkuDto));
    }

    /**
     * 获取产品详细信息
     *
     * @param id id
     * @return {@link AjaxResult }
     */
    @Operation(summary = "商品详情")
    @GetMapping("/item/{id}")
    public AjaxResult getProductDetails(@PathVariable("id") Long id) {
        return AjaxResult.success(productService.getProductDetails(id));
    }

    /**
     * 通过sku id获取
     *
     * @param skuId sku id
     * @return {@link ProductSku }
     */
    @Operation(summary = "根据skuId获取产品详细信息")
    @GetMapping("/getBySkuId/{skuId}")
    public ProductSku getBySkuId(@PathVariable Long skuId) {
        return productService.getBySkuId(skuId);
    }
}
