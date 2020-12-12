package com.xcq.blogserver.core.web;

import com.xcq.blogserver.core.vo.CommonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 处理全局返回结果
 */
@ControllerAdvice(basePackages = "com.xcq.blogserver.controller")
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    /**
     * 返回true表示拦截Controller所有API接口的返回结果
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * 约定成功才返回
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof CommonResult){
            return o;
        }
        return CommonResult.success(o);
    }
}
