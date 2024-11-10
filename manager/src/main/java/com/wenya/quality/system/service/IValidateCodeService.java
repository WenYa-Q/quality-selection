package com.wenya.quality.system.service;

import com.wenya.quality.vo.system.ValidateCodeVo;

/**
 * 验证码服务接口
 *
 * @author wenya
 */
public interface IValidateCodeService {

    /**
     * 获取验证码
     *
     * @return {@link ValidateCodeVo }
     */
    public abstract ValidateCodeVo generateValidateCode();
}
