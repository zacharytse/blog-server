package com.xcq.blogserver.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xcq.blogserver.domain.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    default List<Article> findByTitle(@Param("title") String title) {
        return selectList(new QueryWrapper<Article>().like("title", title));
    }

    default List<Article> findByUID(@Param("uid") Integer uid) {
        return selectList(new QueryWrapper<Article>().eq("uid",uid));
    }
}
