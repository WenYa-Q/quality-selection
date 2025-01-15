package com.wenya.service.impl;

import com.wenya.quality.HttpUtils;
import com.wenya.service.ISmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * sms服务实施
 * Description：
 *
 * @author wuqiulin
 */
@Slf4j
@Service
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @Value("${sms.host}")
    private String host;

    @Value("${sms.path}")
    private String path;

    @Value("${sms.appcode}")
    private String appcode;

    /**
     * 发送验证代码
     *
     * @param phone 电话
     */
    @Override
    public void sendValidateCode(String phone) {
        String code = redisTemplate.opsForValue().get("phone:code:" + phone);
        if(StringUtils.hasText(code)) {
            return;
        }

        String validateCode = RandomStringUtils.randomNumeric(4);
        redisTemplate.opsForValue().set("phone:code:" + phone , validateCode , 5 , TimeUnit.MINUTES) ;
        sendSms(phone , validateCode) ;
    }

    /**
     * 发送短信
     *
     * @param phone        电话
     * @param validateCode 验证代码
     */
    private void sendSms(String phone, String validateCode) {
        String method = "POST";

        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        bodys.put("content", "code:" + validateCode);
        bodys.put("template_id", "CST_ptdie100");
        bodys.put("phone_number", phone);

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);

            log.info("发送短信结果：{}", EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            log.error("发送短信失败", e);
            throw new RuntimeException(e);
        }
    }
}
