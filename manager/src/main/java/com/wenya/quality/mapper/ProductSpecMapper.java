package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品规格映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface ProductSpecMapper extends BaseMapper<ProductSpec> {
}
