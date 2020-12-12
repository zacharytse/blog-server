package com.xcq.blogserver.core.web;

import com.xcq.blogserver.constants.ServiceExceptionEnum;
import com.xcq.blogserver.core.exception.ServiceException;
import com.xcq.blogserver.core.vo.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@ControllerAdvice(basePackages = "com.xcq.blogserver.controller")
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(HttpServletRequest req,
                                                ServiceException ex) {
        logger.debug("[serviceExceptionHandler]", ex);
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理参数不正确
     *
     * @param req
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(HttpServletRequest req,
                                                                       MissingServletRequestParameterException ex) {
        logger.debug("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getCode(),
                ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CommonResult exceptionHandler(HttpServletRequest req, Exception e) {
        logger.debug("[exceptionHandler]",e);
        return CommonResult.error(ServiceExceptionEnum.SYS_ERROR.getCode(),
                ServiceExceptionEnum.SYS_ERROR.getMessage());
    }
}
