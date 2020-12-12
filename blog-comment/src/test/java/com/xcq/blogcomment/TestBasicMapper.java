package com.xcq.blogcomment;

import com.xcq.blogcomment.domain.Comment;
import com.xcq.blogcomment.mapper.CommentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBasicMapper {

    @Autowired
    private CommentMapper mapper;

    @Test
    public void testBasicQuery() {
        List<Comment> comment = mapper.findCommentByArticleId(1);
        System.out.println(comment);
    }
}
