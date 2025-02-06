package com.wenya.service;

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
}
