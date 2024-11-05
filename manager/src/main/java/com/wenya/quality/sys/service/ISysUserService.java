package com.wenya.quality.sys.service;

import com.wenya.quality.dto.system.LoginDto;
import com.wenya.quality.vo.system.LoginVo;

/**
 * 用户业务接口
 *
 * @author wenya
 */
public interface ISysUserService {

    /**
     * 登录
     *
     * @param loginDto 登录dto
     * @return {@link LoginVo }
     */
    public LoginVo login(LoginDto loginDto);
}
