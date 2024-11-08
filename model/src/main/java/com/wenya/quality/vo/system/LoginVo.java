package com.wenya.quality.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "登录成功响应结果实体类")
public class LoginVo {

    @Schema(description = "令牌")
    private String token ;

    @Schema(description = "刷新令牌,可以为空")
    private String refreshToken ;

    @Schema(description = "过期时间")
    private Long expires;

    @Schema(description = "token类型")
    private String tokenType = "String";
}
