package com.wenya.quality.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wenya.quality.doamin.base.ProductUnit;
import com.wenya.quality.service.IProductUnitService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品单元控制器
 * Description：
 *
 * @author wuqiulin
 */
@RestController
@RequestMapping("/product/productUnit")
@Tag(name = "商品单元")
public class ProductUnitController extends BaseController {

    @Resource
    private IProductUnitService productUnitService;

    /**
     * 列表
     *
     * @return {@link AjaxResult }
     */
    @Operation(summary = "列表")
    @GetMapping("/list")
    public AjaxResult list() {
        return success(productUnitService.list(new LambdaQueryWrapper<ProductUnit>().eq(ProductUnit::getIsDeleted, 0)
                .orderByDesc(ProductUnit::getId)));
    }
}
