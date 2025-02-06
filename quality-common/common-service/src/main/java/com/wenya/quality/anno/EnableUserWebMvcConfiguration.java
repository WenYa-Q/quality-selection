package com.wenya.quality.anno;

import com.wenya.quality.Interceptor.UserLoginAuthInterceptor;
import com.wenya.quality.config.UserWebMvcConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用用户web mvc配置
 * Description：
 *
 * @author wuqiulin
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = { UserLoginAuthInterceptor.class , UserWebMvcConfiguration.class})
public @interface EnableUserWebMvcConfiguration {
}
