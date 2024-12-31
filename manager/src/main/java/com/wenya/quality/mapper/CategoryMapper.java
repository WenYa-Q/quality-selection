package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.product.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类别映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 批量插入
     *
     * @param categoryList 类别列表
     * @return int
     */
    int insertCategoryByList(@Param("categoryList") List<Category> categoryList);
}
