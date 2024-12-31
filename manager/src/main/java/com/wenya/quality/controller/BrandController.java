package com.wenya.quality.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.github.pagehelper.PageHelper;
import com.wenya.quality.doamin.product.Brand;
import com.wenya.quality.service.IBrandService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.quality.web.domain.TableDataInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌控制器
 * Description：
 *
 * @author wuqiulin
 */
@RestController
@RequestMapping("/product/brand")
public class BrandController extends BaseController {

    @Resource
    private IBrandService brandService;

    /**
     * 按页面查找
     *
     * @return {@link TableInfo }
     */
    @GetMapping("/{page}/{limit}")
    public TableDataInfo findByPage(@PathVariable int page, @PathVariable int limit){
        PageHelper.startPage(page, limit);
        List<Brand> list = brandService.list(new LambdaQueryWrapper<Brand>().eq(Brand::getIsDeleted, 0));

        return getDataTable(list);
    }

    /**
     * 查找全部
     *
     * @return {@link List }<{@link Brand }>
     */
    @GetMapping("/findAll")
    public AjaxResult findAll(){
        return success(brandService.list(new LambdaQueryWrapper<Brand>().eq(Brand::getIsDeleted, 0)));
    }

    /**
     * 保存
     *
     * @param brand 品牌
     * @return {@link AjaxResult }
     */
    @PostMapping("/save")
    public AjaxResult save(@RequestBody Brand brand){
        return toAjax(brandService.save(brand));
    }

    /**
     * 按id更新
     *
     * @param brand 品牌
     * @return {@link AjaxResult }
     */
    @PutMapping("/updateById")
    public AjaxResult updateById(@RequestBody Brand brand){
        return toAjax(brandService.updateById(brand));
    }

    /**
     * 按id删除
     *
     * @param id id
     * @return {@link AjaxResult }
     */
    @DeleteMapping("/deleteById/{id}")
    public AjaxResult deleteById(@PathVariable Long id){
        return toAjax(brandService.removeById(id));
    }
}
