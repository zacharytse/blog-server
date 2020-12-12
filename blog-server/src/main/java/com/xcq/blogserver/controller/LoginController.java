package com.xcq.blogserver.controller;

import com.xcq.blogserver.constants.ServiceExceptionEnum;
import com.xcq.blogserver.core.vo.CommonResult;
import com.xcq.blogserver.domain.User;
import com.xcq.blogserver.service.IUserService;
import com.xcq.blogserver.utils.CookieUtils;
import com.xcq.blogserver.utils.ObjectConverter;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.UUID;

/**
 * 用户登录
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;

    @Value("${LOGIN_TOKEN}")
    private String LOGIN_TOKEN;

    @PostMapping("")
    public CommonResult<String> login(User user, HttpServletRequest request,
                                      HttpServletResponse response) {
        CommonResult<String> res = userService.userLogin(user);
        if (res.isSuccess()) {
            //登录成功，保存cookie
            String token = res.getData();
            CookieUtils.setCookie(request,response,LOGIN_TOKEN,token);
        }
        return res;
    }

}
