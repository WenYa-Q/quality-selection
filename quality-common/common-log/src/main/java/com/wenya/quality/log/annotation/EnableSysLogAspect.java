package com.wenya.quality.log.annotation;


import com.wenya.quality.log.aspect.SysLogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用sys日志特性
 * Description：
 *
 * @author wuqiulin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SysLogAspect.class)
public @interface EnableSysLogAspect {
}
