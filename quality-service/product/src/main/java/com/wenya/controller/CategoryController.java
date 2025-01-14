package com.wenya.controller;

import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类别控制器
 * Description：
 *
 * @author wuqiulin
 */
@Tag(name = "类别", description = "类别控制器")
@RestController
@RequestMapping("/api/product/category")
public class CategoryController extends BaseController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * 查找类别树
     *
     * @return {@link AjaxResult }
     */
    @Operation(summary = "查找类别树")
    @GetMapping("/findCategoryTree")
    public AjaxResult findCategoryTree() {
        return success(categoryService.findCategoryTree());
    }
}
