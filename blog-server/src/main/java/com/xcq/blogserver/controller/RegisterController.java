package com.xcq.blogserver.controller;

import com.xcq.blogserver.constants.ServiceExceptionEnum;
import com.xcq.blogserver.core.exception.ServiceException;
import com.xcq.blogserver.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @PostMapping("")
    public boolean register(User user) {
        //throw new ServiceException(ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR);
        return false;
    }
}
