package com.wenya.controller;

import com.wenya.quality.dto.h5.UserRegisterDto;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.service.IUserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息控制器
 * Description：
 *
 * @author wuqiulin
 */
@Tag(name = "用户信息", description = "用户信息控制器")
@RestController
@RequestMapping("/api/user/userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 注册
     *
     * @param userRegisterDto 用户注册dto
     * @return {@link AjaxResult }
     */
    @Operation(summary = "注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody UserRegisterDto userRegisterDto) {
        userInfoService.register(userRegisterDto);
        return AjaxResult.success();
    }
}
