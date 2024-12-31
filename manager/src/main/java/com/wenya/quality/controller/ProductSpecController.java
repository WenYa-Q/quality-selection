package com.wenya.quality.controller;

import com.github.pagehelper.PageHelper;
import com.wenya.quality.service.IProductSpecService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.TableDataInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
