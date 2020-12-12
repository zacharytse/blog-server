package com.xcq.blogcomment.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xcq.blogcomment.domain.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    default List<Comment> findCommentByArticleId(@Param("articleId") int articleId) {
        return selectList(new QueryWrapper<Comment>().eq("articleId", articleId));
    }

    default List<Comment> findRangeCommentByParentId(int parentId, int pageNo, int size) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("parentId", parentId);
        Page<Comment> pages = new Page<>(pageNo, size);
        return selectPage(pages, wrapper).getRecords();
    }

    default List<Comment> findRangeParentCommentByArticleId(int articleId, int pageNo, int size) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("articleId", articleId);
        Page<Comment> pages = new Page<>(pageNo, size);
        return selectPage(pages,wrapper).getRecords();
    }
}
