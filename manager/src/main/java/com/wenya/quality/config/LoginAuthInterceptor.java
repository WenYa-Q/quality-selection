package com.wenya.quality.config;

import com.alibaba.fastjson.JSON;
import com.wenya.quality.AuthContextUtil;
import com.wenya.quality.doamin.system.SysUser;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * 登录身份验证拦截器
 *
 * @author wenya
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
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
        //判断是否是跨域请求
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }

        //获取Token
        String token = request.getHeader("Token");

        //判断是否存在用户数据
        if (StringUtils.isNotBlank(token)) {
            String userInfo = stringRedisTemplate.opsForValue().get("user:login" + token);
            if (StringUtils.isNotBlank(userInfo)) {
                //将用户数据存放到ThreadLocal
                SysUser sysUser = JSON.parseObject(userInfo, SysUser.class);
                AuthContextUtil.setAuthContext(sysUser);

                //重置用户数据有效时间
                stringRedisTemplate.expire("user:login" + token,30, TimeUnit.MINUTES);

                return true;
            }
        }

        return false;
    }
}
