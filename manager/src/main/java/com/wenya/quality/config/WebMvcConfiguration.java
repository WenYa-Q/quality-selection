package com.wenya.quality.config;

import com.wenya.quality.properties.UserAuthProperties;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * web mvc配置
 *
 * @author wenya
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private LoginAuthInterceptor loginAuthInterceptor;

    @Resource
    private UserAuthProperties userAuthProperties;

    /**
     * 配置跨域请求
     *
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("*");
    }

    /**
     * 添加拦截器
     *
     * @param registry 登记处
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(userAuthProperties.getNoAuthUrls());
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
