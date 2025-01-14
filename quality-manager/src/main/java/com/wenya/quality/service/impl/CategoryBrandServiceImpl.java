package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.product.Brand;
import com.wenya.quality.doamin.product.CategoryBrand;
import com.wenya.quality.dto.product.CategoryBrandDto;
import com.wenya.quality.mapper.CategoryBrandMapper;
import com.wenya.quality.service.ICategoryBrandService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类品牌服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand> implements ICategoryBrandService {

    @Resource
    private CategoryBrandMapper categoryBrandMapper;


    /**
     * 按页面查找
     *
     * @param categoryBrandDto 品类品牌dto
     * @return {@link List }<{@link CategoryBrand }>
     */
    @Override
    public List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto) {
        return categoryBrandMapper.findByPage(categoryBrandDto);
    }

    /**
     * 按类别id查找品牌
     *
     * @param categoryId 类别id
     * @return {@link List }<{@link CategoryBrand }>
     */
    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        return categoryBrandMapper.findBrandByCategoryId(categoryId);
    }
}
