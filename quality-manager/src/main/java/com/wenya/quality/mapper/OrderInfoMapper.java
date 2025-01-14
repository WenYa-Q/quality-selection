package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.order.OrderInfo;
import com.wenya.quality.doamin.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单信息映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    /**
     * 查询指定日期生产的订单数据
     *
     * @param creatTime 创建时间
     * @return {@link OrderStatistics }
     */
    OrderStatistics selectOrderStatistics(String creatTime);
}
