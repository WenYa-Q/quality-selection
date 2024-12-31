package com.wenya.quality.controller;

import com.github.pagehelper.PageHelper;
import com.wenya.quality.dto.product.CategoryBrandDto;
import com.wenya.quality.service.ICategoryBrandService;
import com.wenya.quality.web.controller.BaseController;
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

    @PostMapping("/{page}/{limit}")
    public TableDataInfo findByPage(@PathVariable int page, @PathVariable int limit,
                                    @RequestBody CategoryBrandDto categoryBrandDto){
        PageHelper.startPage(page, limit);
        return getDataTable(categoryBrandService.findByPage(categoryBrandDto));
    }
}
