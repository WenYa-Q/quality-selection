package com.wenya.quality.controller;

import com.wenya.quality.dto.order.OrderStatisticsDto;
import com.wenya.quality.service.IOrderInfoService;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单信息控制器
 * Description：
 *
 * @author wuqiulin
 */
@Tag(name = "订单信息", description = "订单信息")
@RestController
@RequestMapping("/order/orderInfo")
public class OrderInfoController extends BaseController {

    @Resource
    private IOrderInfoService orderInfoService;

    /**
     * 获取订单统计数据
     *
     * @param orderStatisticsDto 订单统计dto
     * @return {@link AjaxResult }
     */
    @Operation(summary = "获取订单统计数据")
    @GetMapping("/getOrderStatisticsData")
    public AjaxResult getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        return success(orderInfoService.getOrderStatisticsData(orderStatisticsDto));
    }
}
