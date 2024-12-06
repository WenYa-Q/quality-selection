package com.wenya.quality.controller;

import com.wenya.quality.service.ICategoryService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类别控制器
 * Description：
 *
 * @author wuqiulin
 */
@RestController
@RequestMapping("/product/category")
public class CategoryController extends BaseController {

    @Resource
    private ICategoryService categoryService;

    /**
     * 按父id查找
     *
     * @param parentId 父id
     * @return {@link AjaxResult }
     */
    @GetMapping("/findByParentId/{parentId}")
    public AjaxResult findByParentId(@PathVariable Long parentId) {
        return success(categoryService.findByParentId(parentId));
    }
}
