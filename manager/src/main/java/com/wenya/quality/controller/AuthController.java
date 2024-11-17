package com.wenya.quality.controller;

import com.wenya.quality.doamin.system.SysUser;
import com.wenya.quality.dto.system.LoginDto;
import com.wenya.quality.log.annotation.Log;
import com.wenya.quality.log.enums.OperateType;
import com.wenya.quality.service.ISysUserService;
import com.wenya.quality.service.IValidateCodeService;
import com.wenya.quality.vo.common.Result;
import com.wenya.quality.vo.system.ValidateCodeVo;
import com.wenya.quality.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 登录控制器
 *
 * @author wenya
 */
@RestController
@RequestMapping
public class AuthController {
    @Resource
    private ISysUserService sysUserService;

    @Resource
    private IValidateCodeService validateCodeService;

    /**
     * 登录
     *
     * @param loginDto 登录dto
     * @return {@link Result }<{@link ? }> 结果信息
     */
    @Operation(summary = "用户登录")
    @Log(model = "用户模块", description = "用户登录", opType = OperateType.LOGIN)
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginDto loginDto) {
        return AjaxResult.success(sysUserService.login(loginDto));
    }

    /**
     * 获取/生成验证代码
     *
     * @return {@link Result }<{@link ValidateCodeVo }>
     */
    @GetMapping("/generateValidateCode")
    @Operation(summary = "获取验证码")
    public AjaxResult generateValidateCode(){
        return AjaxResult.success(validateCodeService.generateValidateCode());
    }

    /**
     * 获取用户信息
     *
     * @return {@link Result }<{@link SysUser }>
     */
    @GetMapping("/getUserInfo")
    @Operation(summary = "获取用户信息")
    public AjaxResult getUserInfo(HttpServletRequest request){
        //从request获取请求头Token的数据
        String token = request.getHeader("token");
        //获取令牌
        return AjaxResult.success(sysUserService.getUserInfo(token));
    }

    /**
     * 注销
     *
     * @return {@link Result }
     */
    @GetMapping("/logout")
    @Operation(summary = "注销用户")
    public AjaxResult logout(HttpServletRequest request){
        //从request获取请求头Token的数据
        String token = request.getHeader("Token");

        //注销用户
        sysUserService.logout(token);
        return AjaxResult.success();
    }
}
