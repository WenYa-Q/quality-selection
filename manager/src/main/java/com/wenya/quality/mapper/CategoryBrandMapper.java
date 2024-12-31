package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.product.CategoryBrand;
import com.wenya.quality.dto.product.CategoryBrandDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 分类品牌映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface CategoryBrandMapper extends BaseMapper<CategoryBrand> {
    /**
     * 按页面查找
     *
     * @param categoryBrandDto 品类品牌dto
     * @return {@link List }<{@link CategoryBrand }>
     */
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);
}
