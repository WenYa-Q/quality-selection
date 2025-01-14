package com.wenya.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenya.quality.doamin.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统操作日志映射器
 * Description：
 *
 * @author wuqiulin
 */
@Mapper
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
}
