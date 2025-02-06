package com.wenya.controller;

import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.service.ICartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车控制器
 * Description：
 *
 * @author wuqiulin
 */
@Tag(name = "购物车控制器")
@RestController
@RequestMapping("/api/order/cart")
public class CartController extends BaseController {

    @Resource
    private ICartService cartService;

    /**
     * 添加到购物车
     *
     * @param skuId  sku id
     * @param skuNum sku num
     * @return {@link AjaxResult }
     */
    @Operation(summary = "添加到购物车")
    @GetMapping("/auth/addToCart/{skuId}/{skuNum}")
    public AjaxResult addToCart(@PathVariable Long skuId, @PathVariable Integer skuNum) {
        cartService.addToCart(skuId, skuNum);
        return AjaxResult.success();
    }
}
