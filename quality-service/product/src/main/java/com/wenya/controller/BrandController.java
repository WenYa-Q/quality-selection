package com.wenya.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wenya.quality.doamin.product.Brand;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.service.IBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 品牌控制器
 * Description：
 *
 * @author wuqiulin
 */
@Tag(name = "品牌", description = "品牌控制器")
@RestController
@RequestMapping("/api/product/brand")
public class BrandController extends BaseController {

    @Autowired
    private IBrandService brandService;

    /**
     * 查找全部
     *
     * @return {@link AjaxResult }
     */
    @Operation(summary = "查找所有品牌")
    @GetMapping("/findAll")
    public AjaxResult findAll() {
        return success(brandService.list(new LambdaQueryWrapper<Brand>().eq(Brand::getIsDeleted, 0)));
    }
}
