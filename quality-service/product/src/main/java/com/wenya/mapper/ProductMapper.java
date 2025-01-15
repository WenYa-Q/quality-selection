package com.wenya.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
