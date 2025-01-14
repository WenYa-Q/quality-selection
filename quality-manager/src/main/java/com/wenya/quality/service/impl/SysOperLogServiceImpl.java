package com.wenya.quality.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenya.quality.doamin.system.SysOperLog;
import com.wenya.quality.log.annotation.AsyncOperLogService;
import com.wenya.quality.mapper.SysOperLogMapper;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 系统操作日志服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements AsyncOperLogService {

    @Resource
    private SysOperLogMapper sysOperLogMapper;

    /**
     * 保存系统操作日志
     *
     * @param sysOperLog 系统操作日志
     */
    @Async
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
