package com.wenya.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.user.UserInfo;
import com.wenya.quality.dto.h5.UserLoginDto;
import com.wenya.quality.dto.h5.UserRegisterDto;
import com.wenya.quality.vo.h5.UserInfoVo;
import jakarta.servlet.http.HttpServletRequest;

/**
 * user信息服务
 * Description：
 *
 * @author wuqiulin
 */
public interface IUserInfoService extends IService<UserInfo> {
    /**
     * 注册
     *
     * @param userRegisterDto 用户注册dto
     */
    void register(UserRegisterDto userRegisterDto);

    /**
     * 登录
     *
     * @param userLoginDto 用户登录dto
     * @return {@link String }
     */
    String login(UserLoginDto userLoginDto);

    /**
     * 获取当前用户信息
     *
     * @param request 请求
     * @return {@link UserInfoVo }
     */
    UserInfoVo getCurrentUserInfo(HttpServletRequest request);

    /**
     * 注销
     *
     * @param request 请求
     */
    void logout(HttpServletRequest request);
}
