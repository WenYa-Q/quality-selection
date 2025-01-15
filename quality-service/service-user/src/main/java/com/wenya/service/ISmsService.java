package com.wenya.service;

/**
 * sms服务
 * Description：
 *
 * @author wuqiulin
 */
public interface ISmsService {
    /**
     * 发送验证代码
     *
     * @param phone 电话
     */
    void sendValidateCode(String phone);
}
