package com.xcq.blogcomment.service;

import com.xcq.blogcomment.domain.Comment;

import java.util.List;

/**
 * 评论系统的业务层
 */
public interface ICommentService {

    /**
     * 每一层楼都不展开的情况下对评论进行查询
     *
     * @param articleId
     * @return
     */
    List<Comment> findParentCommentByArticleId(int articleId);

    /**
     * 根据父评论的id查询分页后的子评论,查询的页为start,页数为size
     *
     * @param parentId
     * @return
     */
    List<Comment> findRangeChildCommentByParentId(int parentId, int start, int size);

    /**
     * 只查询一页子评论
     *
     * @param parentId
     * @param start
     * @return
     */
    default List<Comment> findOnePageChildCommentByParentId(int parentId, int start) {
        return findRangeChildCommentByParentId(parentId, start, 1);
    }

    List<Comment> findRangeParentCommentByArticleId(int articleId, int pageNo, int size);

    default List<Comment> findOnePageParentCommentByArticleId(int articleId, int pageNo) {
        return findRangeParentCommentByArticleId(articleId, pageNo, 1);
    }
}
