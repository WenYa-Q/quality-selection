package com.wenya.quality.log.aspect;


import com.alibaba.fastjson2.JSON;
import com.wenya.quality.AuthContextUtil;
import com.wenya.quality.doamin.system.SysOperLog;
import com.wenya.quality.log.annotation.AsyncOperLogService;
import com.wenya.quality.log.annotation.Log;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 系统日志切面
 *
 * @author wenya
 */
@Aspect
@Component
public class SysLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysLogAspect.class);

    private static final ThreadLocal<SysOperLog> threadLocal = new ThreadLocal<>();

    @Resource
    private AsyncOperLogService asyncOperLogService;

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
        SysOperLog sysOperLog = new SysOperLog();

        //获取调用的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //获取方法上的注解
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            //获取描述
            sysOperLog.setTitle(log.model() + ":" + log.description());
            //获取操作人类型
            sysOperLog.setOperatorType(log.operatorType().name());

            // 获取目标方法信息
            sysOperLog.setMethod(method.getDeclaringClass().getName());

            // 获取请求相关参数
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                    RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            sysOperLog.setRequestMethod(request.getMethod());
            sysOperLog.setOperUrl(request.getRequestURI());
            sysOperLog.setOperIp(request.getRemoteAddr());

            // 设置请求参数
            if(log.isSaveRequestParam()) {
                String requestMethod = sysOperLog.getRequestMethod();
                if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
                    String params = Arrays.toString(joinPoint.getArgs());
                    sysOperLog.setOperParam(params);
                }
            }
            sysOperLog.setOperName(AuthContextUtil.getAuthContext().getUserName());
        }
        threadLocal.set(sysOperLog);
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

    }

    /**
     * 环绕通知
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取调用的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //获取方法上的注解
        Log log = method.getAnnotation(Log.class);

        Object proceed = joinPoint.proceed();

        SysOperLog sysOperLog = threadLocal.get();
        if (log.isSaveResponseParam()){
            sysOperLog.setJsonResult(JSON.toJSONString(proceed));
            sysOperLog.setStatus(0);
        }

        //保存日志
        asyncOperLogService.saveSysOperLog(sysOperLog);

        return proceed;
    }

    /**
     * 异常通知
     *
     * @param ex        ex
     * @throws Throwable 可抛出
     */
    @AfterThrowing(pointcut = "log()", throwing = "ex")
    public void doAfterThrowing(Throwable ex) throws Throwable {
        SysOperLog sysOperLog = threadLocal.get();
        sysOperLog.setStatus(1);
        sysOperLog.setErrorMsg(ex.getMessage());

        throw ex;
    }
}
