package com.wenya.controller;

import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.service.ICartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车控制器
 * Description：
 *
 * @author wuqiulin
 */
@Tag(name = "购物车控制器")
@RestController
@RequestMapping("/api/order/cart/auth")
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
    @GetMapping("/addToCart/{skuId}/{skuNum}")
    public AjaxResult addToCart(@PathVariable Long skuId, @PathVariable Integer skuNum) {
        cartService.addToCart(skuId, skuNum);
        return AjaxResult.success();
    }

    /**
     * 购物车列表
     *
     * @return {@link AjaxResult }
     */
    @Operation(summary = "购物车列表")
    @GetMapping("/cartList")
    public AjaxResult cartList() {
        return AjaxResult.success(cartService.cartList());
    }

    /**
     * 删除购物车
     *
     * @param skuId sku id
     * @return {@link AjaxResult }
     */
    @Operation(summary = "删除购物车")
    @DeleteMapping("/deleteCart/{skuId}")
    public AjaxResult deleteCart(@PathVariable Long skuId) {
        cartService.deleteCart(skuId);
        return AjaxResult.success();
    }

    /**
     * 修改购物车选中状态
     *
     * @param skuId     sku id
     * @param isChecked 已检查
     * @return {@link AjaxResult }
     */
    @Operation(summary = "修改购物车选中状态")
    @GetMapping("/checkCart/{skuId}/{isChecked}")
    public AjaxResult checkCart(@PathVariable Long skuId, @PathVariable Integer isChecked) {
        cartService.checkCart(skuId, isChecked);
        return AjaxResult.success();
    }

    /**
     * 全选购物车
     *
     * @param isChecked 已检查
     * @return {@link AjaxResult }
     */
    @Operation(summary = "全选购物车")
    @GetMapping("/allCheckCart/{isChecked}")
    public AjaxResult allCheckCart(@PathVariable Integer isChecked) {
        cartService.allCheckCart(isChecked);
        return AjaxResult.success();
    }

    /**
     * 清空购物车
     *
     * @return {@link AjaxResult }
     */
    @Operation(summary = "清空购物车")
    @GetMapping("/clearCart")
    public AjaxResult clearCart() {
        cartService.clearCart();
        return AjaxResult.success();
    }
}
