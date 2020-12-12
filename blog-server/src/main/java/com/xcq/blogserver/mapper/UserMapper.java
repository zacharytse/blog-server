package com.xcq.blogserver.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xcq.blogserver.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserMapper extends BaseMapper<User> {

    default User findByUsername(@Param("username") String username){
        return selectOne(new QueryWrapper<User>().eq("username",username));
    }

    default IPage<User> selectPageByCreateTime(IPage<User> page,
                                               @Param("createTime")Date createTime){
        return selectPage(page,
                new QueryWrapper<User>().gt("create_time",createTime));

    }
}
