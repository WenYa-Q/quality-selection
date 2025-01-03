package com.wenya.quality.controller;

import com.github.pagehelper.PageHelper;
import com.wenya.quality.dto.product.ProductDto;
import com.wenya.quality.service.IProductService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.TableDataInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品控制器
 * Description：
 *
 * @author wuqiulin
 */
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
    @GetMapping("/{pageNum}/{pageSize}")
    public TableDataInfo findByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize, ProductDto productDto) {
        PageHelper.startPage(pageNum, pageSize);
        return getDataTable(productService.findByPage(productDto));
    }
}
