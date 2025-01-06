package com.wenya.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.order.OrderInfo;
import com.wenya.quality.dto.order.OrderStatisticsDto;
import com.wenya.quality.vo.order.OrderStatisticsVo;

/**
 * 订单信息服务
 * Description：
 *
 * @author wuqiulin
 */
public interface IOrderInfoService extends IService<OrderInfo> {
    /**
     * 获取订单统计数据
     *
     * @param orderStatisticsDto 订单统计dto
     * @return {@link OrderStatisticsVo }
     */
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
