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

    /**
     * 根据id获取商品详情
     *
     * @param id id
     * @return {@link AjaxResult }
     */
    @Operation(summary = "根据id获取商品详情")
    @GetMapping("/getById/{id}")
    public AjaxResult getById(@PathVariable Long id) {
        return success(productService.getProductById(id));
    }

    /**
     * 按id更新
     *
     * @param product 产品
     * @return {@link AjaxResult }
     */
    @Operation(summary = "根据ID更新商品")
    @PutMapping("/updateById")
    public AjaxResult updateById(@RequestBody Product product) {
        return success(productService.updateProduct(product));
    }

    /**
     * 按id删除
     *
     * @param id id
     * @return {@link AjaxResult }
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResult deleteById(@PathVariable Long id) {
        return success(productService.deleteById(id));
    }


    /**
     * 更新审核状态
     *
     * @param id     id
     * @param status 状态
     * @return {@link AjaxResult }
     */
    @Operation(summary = "更新审核状态")
    @PutMapping("/updateStatus/{id}/{status}")
    public AjaxResult updateAuditStatus(@PathVariable Long id, @PathVariable Integer status) {
        return success(productService.updateAuditStatus(id, status));
    }

    /**
     * 更新商品上下架状态
     *
     * @param id     id
     * @param status 状态
     * @return {@link AjaxResult }
     */
    @Operation(summary = "更新上下架状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public AjaxResult updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        return success(productService.updateStatus(id, status));
    }
}
