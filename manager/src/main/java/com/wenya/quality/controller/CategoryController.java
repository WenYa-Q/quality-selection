package com.wenya.quality.controller;

import com.wenya.quality.service.ICategoryService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 导出数据
     *
     * @param response 响应
     */
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }

    /**
     * 导入数据
     *
     * @param multipartFile 文件
     * @return {@link AjaxResult }
     */
    @PostMapping("/importData")
    public AjaxResult importData(@RequestPart(value = "file") MultipartFile multipartFile) {
        categoryService.importData(multipartFile);
        return success();
    }
}
