package com.wenya.controller;

import com.wenya.quality.web.controller.BaseController;
import com.wenya.quality.web.domain.AjaxResult;
import com.wenya.service.ISmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sms控制器
 * Description：
 *
 * @author wuqiulin
 */
@Tag(name = "短信", description = "短信控制器")
@RestController
@RequestMapping("/api/user/sms")
public class SmsController extends BaseController {

    @Autowired
    private ISmsService smsService;

    /**
     * 发送验证代码
     *
     * @param phone 电话
     * @return {@link AjaxResult }
     */
    @Operation(summary = "发送验证代码")
    @GetMapping("/sendValidateCode")
    public AjaxResult sendValidateCode(String phone) {
        smsService.sendValidateCode(phone);
        return success();
    }
}
