package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.product.Category;
import com.wenya.quality.mapper.CategoryMapper;
import com.wenya.quality.service.ICategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 类别服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 按父id查找
     *
     * @param parentId 父id
     * @return {@link String }
     */
    @Override
    public List<Category> findByParentId(Long parentId) {
        //查找父级为parentId的类别
        List<Category> categoryList = categoryMapper
                .selectList(new QueryWrapper<Category>()
                        .eq("parent_id", parentId).eq("is_deleted", 0));

        if (!CollectionUtils.isEmpty(categoryList)) {
            //判断是否存在子节点
            categoryList.forEach(item -> {
                List<Category> childrenList = categoryMapper.selectList(new QueryWrapper<Category>()
                        .eq("id", item.getId()).eq("is_deleted", 0));
                item.setHasChildren(! CollectionUtils.isEmpty(childrenList));
            });
        }

        return categoryList;
    }
}
