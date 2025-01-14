package com.wenya.quality.log.annotation;

import com.wenya.quality.doamin.system.SysOperLog;

/**
 * 异步操作日志服务
 * Description：
 *
 * @author wuqiulin
 */
public interface AsyncOperLogService {

    /**
     * 保存系统操作日志
     *
     * @param sysOperLog 系统操作日志
     */
    void saveSysOperLog(SysOperLog sysOperLog);
}
