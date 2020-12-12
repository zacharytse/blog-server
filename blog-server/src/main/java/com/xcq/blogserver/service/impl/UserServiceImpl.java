package com.xcq.blogserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcq.blogserver.constants.ServiceExceptionEnum;
import com.xcq.blogserver.core.vo.CommonResult;
import com.xcq.blogserver.domain.User;
import com.xcq.blogserver.mapper.UserMapper;
import com.xcq.blogserver.service.IUserService;
import com.xcq.blogserver.utils.ObjectConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.Duration;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public CommonResult<String> userLogin(User user) {
        User registeredUser = findByUsername(user.getUsername());
        if (registeredUser != null) {
            if (DigestUtils.md5DigestAsHex(registeredUser.getPassword().getBytes())
                    .equals(user.getPassword())) {
                logger.debug("[login][password validate pass]");
                return CommonResult.success(saveLoginSession(user));
            } else {
                logger.debug("[login error][password is error]" +
                                "[correct pwd:{}][error pwd:{}]",
                        DigestUtils.md5DigestAsHex(registeredUser.getUsername().getBytes()),
                        user.getPassword());
                return CommonResult.error(ServiceExceptionEnum.PASSWORD_NOT_TRUE.getCode(),
                        ServiceExceptionEnum.PASSWORD_NOT_TRUE.getMessage());
            }
        }
        logger.debug("[login error][user not found]",user);
        return CommonResult.error(ServiceExceptionEnum.USER_NOT_FOUND.getCode(),
                ServiceExceptionEnum.USER_NOT_FOUND.getMessage());
    }

    /**
     * 将登录用户的信息保存到Redis中
     * @param user
     */
    private String saveLoginSession(User user) {
        String token = UUID.randomUUID().toString().replace("-","");
        user.setPassword(null);
        user.setEmail(null);
        //将用户登录信息存入到redis中
        stringRedisTemplate.opsForValue().set("SESSION:" + token, ObjectConverter.toJson(user));
        //设置session过期时间
        stringRedisTemplate.expire("SESSION:" + token, Duration.ofSeconds(SESSION_EXPIRE));
        return token;
    }
}
