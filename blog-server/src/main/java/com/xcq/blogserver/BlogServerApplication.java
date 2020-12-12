package com.xcq.blogserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = {
        "classpath:resource.properties",
        "classpath:application.properties"
})
@SpringBootApplication
@MapperScan(basePackages = "com.xcq.blogserver.mapper")//指定mybatis-plus扫描的包
public class BlogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogServerApplication.class, args);
    }

}
