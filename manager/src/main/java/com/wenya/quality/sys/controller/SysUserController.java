package com.wenya.quality.sys.controller;


import com.wenya.quality.log.annotation.Log;
import com.wenya.quality.log.enums.OperateType;
import com.wenya.quality.sys.service.ISysUserService;
import com.wenya.quality.dto.system.LoginDto;
import com.wenya.quality.sys.service.IValidateCodeService;
import com.wenya.quality.vo.common.Result;
import com.wenya.quality.vo.common.ResultCodeEnum;
import com.wenya.quality.vo.system.LoginVo;
import com.wenya.quality.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * sys用户控制器
 *
 * @author wenya
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

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
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        return Result.build(sysUserService.login(loginDto), ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/generateValidateCode")
    @Operation(summary = "获取验证码")
    public Result<ValidateCodeVo> generateValidateCode(){
        return Result.build(validateCodeService.generateValidateCode(), ResultCodeEnum.SUCCESS);
    }
}
