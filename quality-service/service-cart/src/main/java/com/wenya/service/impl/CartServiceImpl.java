package com.wenya.service.impl;

import com.alibaba.fastjson2.JSON;
import com.wenya.product.ProductFeignClient;
import com.wenya.quality.AuthContextUtil;
import com.wenya.quality.doamin.h5.CartInfo;
import com.wenya.quality.doamin.product.ProductSku;
import com.wenya.service.ICartService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class CartServiceImpl implements ICartService {

    @Resource
    private RedisTemplate<String , String> redisTemplate;

    @Resource
    private ProductFeignClient productFeignClient;

    /**
     * 添加到购物车
     *
     * @param skuId  sku id
     * @param skuNum sku num
     */
    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        //获取当前登录用户id
        Long id = AuthContextUtil.getUserInfo().getId();
        System.out.println("用户id：" + id + "添加到购物车，skuId：" + skuId + "，skuNum：" + skuNum);

        //获取缓存对象
        Object cartInfoObj = redisTemplate.opsForHash().get("user:cart:" + id, String.valueOf(skuId));
        CartInfo cartInfo;
        if (cartInfoObj != null) {
            cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        } else {
            cartInfo = new CartInfo();
            ProductSku productSku = productFeignClient.getBySkuId(skuId);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(id);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }

        // 将商品数据存储到购物车中
        redisTemplate.opsForHash().put("user:cart:" + id , String.valueOf(skuId) , JSON.toJSONString(cartInfo));
    }

    /**
     * 购物车列表
     *
     * @return {@link List }<{@link CartInfo }>
     */
    @Override
    public List<CartInfo> cartList() {
        //获取当前登录用户id
        Long id = AuthContextUtil.getUserInfo().getId();
        System.out.println("用户id：" + id + "查询购物车列表");

        //获取缓存对象
        List<Object> cartInfoList = redisTemplate.opsForHash().values("user:cart:" + id);
        if (!CollectionUtils.isEmpty(cartInfoList)) {

            return cartInfoList.stream().map(cartInfoObj -> JSON.parseObject(cartInfoObj.toString(), CartInfo.class))
                    .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 删除购物车
     *
     * @param skuId sku id
     */
    @Override
    public void deleteCart(Long skuId) {
        //获取当前登录用户id
        Long id = AuthContextUtil.getUserInfo().getId();
        System.out.println("用户id：" + id + "删除购物车，skuId：" + skuId);

        //删除购物车中的商品
        redisTemplate.opsForHash().delete("user:cart:" + id , String.valueOf(skuId));
    }

    /**
     * 修改购物车选中状态
     *
     * @param skuId     sku id
     * @param isChecked 已检查
     */
    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        //获取当前登录用户id
        Long id = AuthContextUtil.getUserInfo().getId();

        //获取缓存对象
        Boolean hasKey = redisTemplate.opsForHash().hasKey("user:cart:" + id, String.valueOf(skuId));
        if (hasKey) {
            Object cartInfoObj = redisTemplate.opsForHash().get("user:cart:" + id, String.valueOf(skuId));
            assert cartInfoObj != null;
            CartInfo cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            cartInfo.setUpdateTime(new Date());
            //将商品数据存储到购物车中
            redisTemplate.opsForHash().put("user:cart:" + id , String.valueOf(skuId) , JSON.toJSONString(cartInfo));
        }
    }
}
