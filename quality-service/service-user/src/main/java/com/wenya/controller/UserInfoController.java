package com.wenya.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.wenya.quality.dto.h5.UserLoginDto;
import com.wenya.quality.dto.h5.UserRegisterDto;
import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.service.IUserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 登录
     *
     * @param userLoginDto 用户登录dto
     * @return {@link AjaxResult }
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody UserLoginDto userLoginDto) {
        return AjaxResult.success(userInfoService.login(userLoginDto));
    }

    /**
     * 获取当前用户信息
     *
     * @return {@link AjaxResult }
     */
    @Operation(summary = "获取当前用户信息")
    @GetMapping("/getCurrentUserInfo")
    public AjaxResult getCurrentUserInfo(HttpServerRequest request) {
        return AjaxResult.success(userInfoService.getCurrentUserInfo(request));
    }
}
