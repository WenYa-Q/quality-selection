package com.wenya.service.impl;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.mapper.CategoryMapper;
import com.wenya.quality.doamin.product.Category;
import com.wenya.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类别服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查找类别树
     *
     * @return {@link List }<{@link Category }>
     */
    @Override
    public List<Category> findCategoryTree() {
        //获取所有数据
        List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .eq(Category::getIsDeleted, 0)
                .eq(Category::getStatus, 1)
                .orderByAsc(Category::getOrderNum));

        //查找一级分类数据
        List<Category> level1Classification = categoryList.stream().filter(category -> category.getParentId() == 0)
                .collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(level1Classification)) {
            level1Classification.forEach(category -> {
                //查找二级分类数据
                List<Category> level2Classification = categoryList.stream().filter(category1 -> category1.getParentId().equals(category.getId()))
                        .collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(level2Classification)) {
                    level2Classification.forEach(category1 -> {
                        //查找三级分类数据
                        List<Category> level3Classification = categoryList.stream().filter(category2 -> category2.getParentId().equals(category1.getId()))
                                .collect(Collectors.toList());
                        category1.setChildren(level3Classification);
                    });
                }
                category.setChildren(level2Classification);
            });
        }
        return level1Classification;
    }
}
