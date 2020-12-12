package com.xcq.blogserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xcq.blogserver.core.vo.CommonResult;
import com.xcq.blogserver.domain.User;

/**
 * 定义用户查询接口
 */
public interface IUserService extends IService<User> {

    User findByUsername(String username);

    CommonResult<String> userLogin(User user);
}
