package com.wenya.service;

import com.wenya.quality.doamin.h5.CartInfo;

import java.util.List;

/**
 * 购物车服务
 * Description：
 *
 * @author wuqiulin
 */
public interface ICartService {
    /**
     * 添加到购物车
     *
     * @param skuId  sku id
     * @param skuNum sku num
     */
    void addToCart(Long skuId, Integer skuNum);

    /**
     * 购物车列表
     *
     * @return {@link List }<{@link CartInfo }>
     */
    List<CartInfo> cartList();

    /**
     * 删除购物车
     *
     * @param skuId sku id
     */
    void deleteCart(Long skuId);

    /**
     * 修改购物车选中状态
     *
     * @param skuId     sku id
     * @param isChecked 已检查
     */
    void checkCart(Long skuId, Integer isChecked);

    /**
     * 全选购物车
     *
     * @param isChecked 已检查
     */
    void allCheckCart(Integer isChecked);
}
