package com.wenya.quality.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(description = "用户登录请求参数")
public class LoginDto {

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName ;

    @Schema(description = "密码")
    @NotBlank(message = "请填写密码")
    private String password ;

    @Schema(description = "提交验证码")
    private String captcha ;

    @Schema(description = "验证码key")
    private String codeKey ;

}
