package com.wenya.quality.log.annotation;

import com.wenya.quality.log.enums.OperateType;
import com.wenya.quality.log.enums.OperatorType;

import java.lang.annotation.*;

/**
 * 系统日志切面
 *
 * @author wenya
 */
//目标方法
@Target({ElementType.PARAMETER, ElementType.METHOD})
//注解会在class中存在，运行时可通过反射获取
@Retention(RetentionPolicy.RUNTIME)
////文档生成时，该注解将被包含在javadoc中
@Documented
@Inherited
public @interface Log {

    /**
     * 描述
     *
     * @return {@link String }
     */
    String description() default "";

    /**
     * 操作人类型
     *
     * @return {@link OperatorType }
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 模块名称
     *
     * @return {@link String }
     */
    String model();

    /**
     * 操作类型
     *
     * @return {@link OperateType }
     */
    OperateType opType();

    /**
     * 是保存请求参数
     *
     * @return boolean
     */
    public boolean isSaveRequestParam() default true;

    /**
     * 是保存响应参数
     *
     * @return boolean
     */
    public boolean isSaveResponseParam() default true;
}
