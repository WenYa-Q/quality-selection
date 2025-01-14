package com.wenya.controller;

import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.service.IndexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制器
 * Description：
 *
 * @author wuqiulin
 */
@Tag(name = "首页", description = "首页控制器")
@RestController
@RequestMapping("/api/product/index")
public class indexController extends BaseController {

    @Autowired
    private IndexService indexService;

    /**
     * 查找所有数据
     *
     * @return {@link AjaxResult }
     */
    @Operation(summary = "查找所有数据")
    @GetMapping
    public AjaxResult findAllData() {
        return success(indexService.findAllData());
    }
}
