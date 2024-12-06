package com.wenya.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.product.Category;

import java.util.List;

/**
 * 类别服务
 * Description：
 *
 * @author wuqiulin
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 按父id查找
     *
     * @param parentId 父id
     * @return {@link String }
     */
    List<Category> findByParentId(Long parentId);
}
