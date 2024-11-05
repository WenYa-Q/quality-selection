package com.wenya.quality.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web mvc配置
 *
 * @author wenya
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

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
}
