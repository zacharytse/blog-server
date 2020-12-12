package com.xcq.blogserver.utils;

import com.alibaba.fastjson.JSON;

/**
 * 将对象转换成各种格式
 */
public class ObjectConverter {

    /**
     * 将对象转换为json字符串
     * @param o
     * @return
     */
    public static String toJson(Object o){
        return JSON.toJSONString(o);
    }
}
