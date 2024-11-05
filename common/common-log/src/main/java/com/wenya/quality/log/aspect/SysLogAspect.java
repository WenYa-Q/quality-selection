package com.wenya.quality.log.aspect;


import com.wenya.quality.log.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 系统日志切面
 *
 * @author wenya
 */
@Aspect
@Component
public class SysLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysLogAspect.class);

    /**
     * 日志切入点
     */
    @Pointcut("@annotation(com.wenya.quality.log.annotation.Log)")
    private void log(){}

    /**
     * 前置通知
     *
     * @param joinPoint 连接点
     */
    @Before(value = "log()")
    public void before(JoinPoint joinPoint) {
        //获取调用的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //获取方法上的注解
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            LOGGER.info("方法调用前：类名：{} 方法名：{} 模块名：{} 操作类型：{}",
                    joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), log.model(), log.opType().toString());
            return;
        }
        //获取调用的方法名称
        LOGGER.info("方法调用前：类名：{} 方法名：{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
    }

    /**
     * 后置通知
     *
     * @param joinPoint 连接点
     */
    @After(value = "log()")
    public void after(JoinPoint joinPoint) {
        LOGGER.info("方法参数：{}", joinPoint.getArgs());
        LOGGER.info("{} || {}", joinPoint.getStaticPart().getId(), joinPoint.getSignature());

        LOGGER.info("方法调用结束：类名：{} 方法名：{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
    }

    /**
     * 环绕通知
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        return joinPoint.proceed();
    }

    /**
     * 异常通知
     *
     * @param ex        ex
     * @throws Throwable 可抛出
     */
    @AfterThrowing(pointcut = "log()", throwing = "ex")
    public void doAfterThrowing(Throwable ex) throws Throwable {
        LOGGER.error("方法异常，异常信息：{}", ex.getMessage());
        throw ex;
    }
}
