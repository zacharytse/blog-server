package com.xcq.blogcomment.controller;

import com.xcq.blogcomment.domain.Comment;
import com.xcq.blogcomment.service.ICommentService;
import com.xcq.blogserver.core.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService service;

    /**
     * 根据文章id获取评论
     *
     * @param articleId
     */
    @GetMapping("/page")
    public CommonResult<List<Comment>> getCommentByPageNo(
            @RequestParam int articleId,
            @RequestParam int pageNo) {
        List<Comment> pageComment = service.findOnePageParentCommentByArticleId(articleId, pageNo);
        return CommonResult.success(pageComment);
    }
}
