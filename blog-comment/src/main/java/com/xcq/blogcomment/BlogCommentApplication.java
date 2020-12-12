package com.xcq.blogcomment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xcq.blogcomment.mapper")
public class BlogCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogCommentApplication.class, args);
    }

}
