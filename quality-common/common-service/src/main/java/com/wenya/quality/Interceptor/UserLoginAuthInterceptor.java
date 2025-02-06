package com.wenya.quality.Interceptor;

import com.alibaba.fastjson2.JSON;
import com.wenya.quality.AuthContextUtil;
import com.wenya.quality.doamin.user.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 用户登录身份验证拦截器
 * Description：
 *
 * @author wuqiulin
 */
@Component
public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    /**
     * 前置
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理程序
     * @return boolean
     * @throws Exception 例外
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果token不为空，那么此时验证token的合法性
        String userInfo = redisTemplate.opsForValue().get("user:spzx:token:" + request.getHeader("token"));
        AuthContextUtil.setUserInfo(JSON.parseObject(userInfo , UserInfo.class));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
