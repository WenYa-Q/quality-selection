package com.wenya.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.product.CategoryBrand;
import com.wenya.quality.dto.product.CategoryBrandDto;

import java.util.List;

/**
 * 分类品牌服务
 * Description：
 *
 * @author wuqiulin
 */
public interface ICategoryBrandService extends IService<CategoryBrand> {
    /**
     * 按页面查找
     *
     * @param categoryBrandDto 品类品牌dto
     * @return {@link List }<{@link ? }>
     */
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);

    /**
     * 按类别id查找品牌
     *
     * @param categoryId 类别id
     * @return {@link List }<{@link CategoryBrand }>
     */
    List<CategoryBrand> findBrandByCategoryId(Long categoryId);
}
