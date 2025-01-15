package com.wenya.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenya.quality.doamin.user.UserInfo;
import com.wenya.quality.dto.h5.UserRegisterDto;

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
}
