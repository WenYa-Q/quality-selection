package com.wenya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品详细信息映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface ProductDetailsMapper extends BaseMapper<ProductDetails> {
}
