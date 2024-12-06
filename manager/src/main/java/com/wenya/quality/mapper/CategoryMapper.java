package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.product.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类别映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
