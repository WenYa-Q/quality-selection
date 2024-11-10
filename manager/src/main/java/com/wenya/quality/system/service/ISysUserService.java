package com.wenya.quality.system.service;

import com.wenya.quality.doamin.system.SysUser;
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

    /**
     * 获取用户信息
     *
     * @param token 令牌
     * @return {@link SysUser }
     */
    public SysUser getUserInfo(String token);

    /**
     * 注销
     *
     * @param token 令牌
     */
    void logout(String token);
}
