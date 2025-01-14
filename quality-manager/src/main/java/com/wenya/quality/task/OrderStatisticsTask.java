package com.wenya.quality.task;

import cn.hutool.core.date.DateUtil;
import com.wenya.quality.doamin.order.OrderStatistics;
import com.wenya.quality.mapper.OrderInfoMapper;
import com.wenya.quality.mapper.OrderStatisticsMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 订单统计任务
 * Description：
 *
 * @author wuqiulin
 */
@Component
@Slf4j
public class OrderStatisticsTask {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private OrderStatisticsMapper orderStatisticsMapper;

    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics(){
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));

        //查找指定日期的订单统计数据
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createTime);

        //插入订单统计数据表中
        if (! Objects.isNull(orderStatistics)) {
            orderStatisticsMapper.insert(orderStatistics);
        }
    }
}
