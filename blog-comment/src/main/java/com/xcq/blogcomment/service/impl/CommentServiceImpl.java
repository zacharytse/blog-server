package com.xcq.blogcomment.service.impl;

import com.xcq.blogcomment.domain.Comment;
import com.xcq.blogcomment.mapper.CommentMapper;
import com.xcq.blogcomment.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应该先配置缓存，后面配
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper mapper;

    @Override
    public List<Comment> findParentCommentByArticleId(int articleId) {
        return mapper.findCommentByArticleId(articleId);
    }

    @Override
    public List<Comment> findRangeChildCommentByParentId(int parentId, int pageNo, int size) {
        return mapper.findRangeCommentByParentId(parentId, pageNo, size);
    }

    @Override
    public List<Comment> findRangeParentCommentByArticleId(int articleId, int pageNo, int size) {
        return mapper.findRangeParentCommentByArticleId(articleId, pageNo, size);
    }
}
