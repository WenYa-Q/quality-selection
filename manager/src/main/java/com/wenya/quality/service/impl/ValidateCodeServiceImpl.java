package com.wenya.quality.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.wenya.quality.service.IValidateCodeService;
import com.wenya.quality.vo.system.ValidateCodeVo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 验证码服务
 *
 * @author wenya
 */
@Service("validateCodeService")
public class ValidateCodeServiceImpl implements IValidateCodeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取验证码
     *
     * @return {@link ValidateCodeVo }
     */
    @Override
    public ValidateCodeVo generateValidateCode() {
        //使用hutool工具包生成图形验证码
        //参数宽 高 验证码数 干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 20);

        String code = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();

        //生成UUID作为图形验证码作为key
        String redisKey = UUID.randomUUID().toString().replace("-", "");

        //存储到Redis内
        stringRedisTemplate.opsForValue().set("user:login:validatecode:" + redisKey, code, 5, TimeUnit.MINUTES);

        //构建响应结果数据
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(redisKey);
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);

        return validateCodeVo;
    }
}
