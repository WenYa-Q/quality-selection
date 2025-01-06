package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单统计映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface OrderStatisticsMapper extends BaseMapper<OrderStatistics> {
}
