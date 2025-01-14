package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.product.Product;
import com.wenya.quality.dto.product.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 产品映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 按页面查找
     *
     * @return {@link List }<{@link Product }>
     */
    List<Product> findByPage(ProductDto productDto);
}
