package com.xcq.blogserver.common.aop;

import com.xcq.blogserver.constants.ServiceExceptionEnum;
import com.xcq.blogserver.core.exception.ServiceException;
import com.xcq.blogserver.core.vo.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoginAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(* com.xcq.blogserver.controller.LoginController.*(..))")
    public void login() {
    }

    /**
     * 检查cookie中的值token在redis中是否存在，若存在，则直接返回登录成功
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("login()")
    public Object aroundLoginAspect(ProceedingJoinPoint proceedingJoinPoint) {
        CommonResult<String> retVal = null;
        try {
            Object[] args = proceedingJoinPoint.getArgs();
            retVal = validateFromRedis(args);
            if (retVal.isError()) {
                logger.debug("[aroundLoginAspect][first login in this device]");
                retVal = (CommonResult<String>) proceedingJoinPoint.proceed(args);
            }
            return retVal;
        } catch (Throwable t) {
            throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
        }
    }

    private CommonResult<String> validateFromRedis(Object[] args) {
        HttpServletRequest req = null;
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                req = (HttpServletRequest) arg;
                break;
            }
        }
        if (req == null) {
            return CommonResult.error(
                    ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getCode(),
                    ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
        }
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return CommonResult.error(ServiceExceptionEnum.REDIS_NOT_FOUND.getCode(),
                    ServiceExceptionEnum.REDIS_NOT_FOUND.getMessage());
        }
        for (Cookie cookie : cookies) {
            logger.debug("[token:{}]", cookie.getValue());
            if (stringRedisTemplate.hasKey("SESSION:" + cookie.getValue())) {
                logger.debug("[validateFromRedis][login again in this device]");
                return CommonResult.success(cookie.getValue());
            }
        }
        return CommonResult.error(ServiceExceptionEnum.REDIS_NOT_FOUND.getCode(),
                ServiceExceptionEnum.REDIS_NOT_FOUND.getMessage());
    }
}
