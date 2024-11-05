package com.wenya.quality.exception;

import com.wenya.quality.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * 业务自定义异常
 *
 * @author wenya
 */
@Data
public class BusinessCustomizeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 信息
     */
    private String message;

    /**
     * 状态码
     */
    private Integer code;

    private ResultCodeEnum resultCodeEnum;

    public BusinessCustomizeException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public BusinessCustomizeException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
