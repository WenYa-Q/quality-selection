package com.wenya.quality.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.order.OrderInfo;
import com.wenya.quality.doamin.order.OrderStatistics;
import com.wenya.quality.dto.order.OrderStatisticsDto;
import com.wenya.quality.mapper.OrderInfoMapper;
import com.wenya.quality.mapper.OrderStatisticsMapper;
import com.wenya.quality.vo.order.OrderStatisticsVo;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单信息服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Resource
    private OrderStatisticsMapper orderStatisticsMapper;

    /**
     * 获取订单统计数据
     *
     * @param orderStatisticsDto 订单统计dto
     * @return {@link OrderStatisticsVo }
     */
    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        LambdaQueryWrapper<OrderStatistics> orderStatisticsLambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(orderStatisticsDto.getCreateTimeBegin()) && StringUtils.isNotEmpty(orderStatisticsDto.getCreateTimeBegin())) {
            orderStatisticsLambdaQueryWrapper.ge(OrderStatistics::getOrderDate, orderStatisticsDto.getCreateTimeBegin());
        }

        if (StringUtils.isNotBlank(orderStatisticsDto.getCreateTimeEnd()) && StringUtils.isNotEmpty(orderStatisticsDto.getCreateTimeEnd())) {
            orderStatisticsLambdaQueryWrapper.le(OrderStatistics::getOrderDate, orderStatisticsDto.getCreateTimeEnd());
        }

        //获取结果统计数据
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsLambdaQueryWrapper
                .orderByAsc(OrderStatistics::getOrderDate));

        //收集日期列表
        List<String> dateList = orderStatisticsList.stream()
                .map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd")).collect(Collectors.toList());

        //统计金额列表
        List<BigDecimal> amountList = orderStatisticsList.stream().map(OrderStatistics::getTotalAmount).collect(Collectors.toList());

        return new OrderStatisticsVo(dateList, amountList);
    }
}
