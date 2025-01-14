package com.wenya.quality.controller;

import com.github.pagehelper.PageHelper;
import com.wenya.quality.doamin.product.CategoryBrand;
import com.wenya.quality.dto.product.CategoryBrandDto;
import com.wenya.quality.service.ICategoryBrandService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.quality.web.domain.TableDataInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 分类品牌控制器
 * Description：
 *
 * @author wuqiulin
 */
@RestController
@RequestMapping("/product/categoryBrand")
public class CategoryBrandController extends BaseController {

    @Resource
    private ICategoryBrandService categoryBrandService;

    /**
     * 按页面查找
     *
     * @param page             页
     * @param limit            页数
     * @param categoryBrandDto 品类品牌dto
     * @return {@link TableDataInfo }
     */
    @PostMapping("/{page}/{limit}")
    public TableDataInfo findByPage(@PathVariable int page, @PathVariable int limit,
                                    @RequestBody CategoryBrandDto categoryBrandDto){
        PageHelper.startPage(page, limit);
        return getDataTable(categoryBrandService.findByPage(categoryBrandDto));
    }

    /**
     * 保存
     *
     * @param categoryBrand 品类品牌
     * @return {@link AjaxResult }
     */
    @PostMapping("/save")
    public AjaxResult save(@RequestBody CategoryBrand categoryBrand){
        return success(categoryBrandService.save(categoryBrand));
    }

    /**
     * 按id更新
     *
     * @param categoryBrand 品类品牌
     * @return {@link AjaxResult }
     */
    @PutMapping("/updateById")
    public AjaxResult updateById(@RequestBody CategoryBrand categoryBrand){
        return success(categoryBrandService.updateById(categoryBrand));
    }

    /**
     * 按id删除
     *
     * @param id id
     * @return {@link AjaxResult }
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResult deleteById(@PathVariable Long id){
        return success(categoryBrandService.removeById(id));
    }

    /**
     * 按类别id查找品牌
     *
     * @param categoryId 类别id
     * @return {@link AjaxResult }
     */
    @GetMapping("/findBrandByCategoryId/{categoryId}")
    public AjaxResult findBrandByCategoryId(@PathVariable Long categoryId) {
        return success(categoryBrandService.findBrandByCategoryId(categoryId));
    }
}
