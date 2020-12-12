package com.xcq.blogserver.core.exception;

import com.xcq.blogserver.constants.ServiceExceptionEnum;

/**
 * 定义业务异常
 */
public class ServiceException extends RuntimeException {

    /**
     * 定义错误码
     */
    private Integer code;

    public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        super(serviceExceptionEnum.getMessage());
        this.code = serviceExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
