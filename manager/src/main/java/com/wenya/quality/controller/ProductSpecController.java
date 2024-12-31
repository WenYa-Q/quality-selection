package com.wenya.quality.controller;

import com.github.pagehelper.PageHelper;
import com.wenya.quality.doamin.product.ProductSpec;
import com.wenya.quality.service.IProductSpecService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.quality.web.domain.TableDataInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 产品规格控制器
 * Description：
 *
 * @author wuqiulin
 */
@RestController
@RequestMapping("/product/productSpec")
public class ProductSpecController extends BaseController {

    @Resource
    private IProductSpecService productSpecService;

    /**
     * 按页面查找
     *
     * @param page  第页
     * @param limit 限制
     * @return {@link TableDataInfo }
     */
    @GetMapping("/{page}/{limit}")
    public TableDataInfo findByPage(@PathVariable int page, @PathVariable int limit){
        PageHelper.startPage(page, limit);
        return getDataTable(productSpecService.list());
    }

    /**
     * 保存
     *
     * @param productSpec 产品规格
     * @return {@link AjaxResult }
     */
    @PostMapping("/save")
    public AjaxResult save(@RequestBody ProductSpec productSpec){
        return success(productSpecService.save(productSpec));
    }

    /**
     * 按id更新
     *
     * @param productSpec 产品规格
     * @return {@link AjaxResult }
     */
    @PutMapping("/updateById")
    public AjaxResult updateById(@RequestBody ProductSpec productSpec){
        return success(productSpecService.updateById(productSpec));
    }

    /**
     * 按id删除
     *
     * @param id id
     * @return {@link AjaxResult }
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResult deleteById(@PathVariable Long id){
        return success(productSpecService.removeById(id));
    }
}
