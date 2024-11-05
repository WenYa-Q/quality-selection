package com.wenya.quality.config;

import com.wenya.quality.vo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author wenya
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<?> error(Exception e) {
        e.printStackTrace();
        return Result.build(null,201, "错误信息：" + e.getMessage());
    }
}
