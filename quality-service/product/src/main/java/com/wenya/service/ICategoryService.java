package com.wenya.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.product.Category;

import java.util.List;

/**
 * category服务
 * Description：
 *
 * @author wuqiulin
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 查找类别树
     *
     * @return {@link List }<{@link Category }>
     */
    List<Category> findCategoryTree();
}
