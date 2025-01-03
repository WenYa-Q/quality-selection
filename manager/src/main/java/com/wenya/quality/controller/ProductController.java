package com.wenya.quality.controller;

import com.github.pagehelper.PageHelper;
import com.wenya.quality.doamin.product.Product;
import com.wenya.quality.dto.product.ProductDto;
import com.wenya.quality.service.IProductService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.quality.web.domain.TableDataInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 产品控制器
 * Description：
 *
 * @author wuqiulin
 */
@Tag(name = "商品管理")
@RestController
@RequestMapping("/product/product")
public class ProductController extends BaseController {

    @Resource
    private IProductService productService;

    /**
     * 按页面查找
     *
     * @return {@link TableDataInfo }
     */
    @Operation(summary = "分页查询")
    @GetMapping("/{pageNum}/{pageSize}")
    public TableDataInfo findByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize, ProductDto productDto) {
        PageHelper.startPage(pageNum, pageSize);
        return getDataTable(productService.findByPage(productDto));
    }


    /**
     * 保存
     *
     * @param product 产品
     * @return {@link AjaxResult }
     */
    @Operation(summary = "新增")
    @PostMapping("/save")
    public AjaxResult save(@RequestBody Product product) {
        return toAjax(productService.saveProduct(product));
    }
}
