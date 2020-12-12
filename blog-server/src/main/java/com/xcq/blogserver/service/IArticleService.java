package com.xcq.blogserver.service;

import com.xcq.blogserver.domain.Article;

import java.util.List;

/**
 * 定义文章操作的接口
 */
public interface IArticleService {

    /**
     * 保存文章
     * @param article
     */
    void saveArticle(Article article);

    /**
     * 根据标题查询文章(支持模糊查询)
     * @param title
     * @return
     */
    List<Article> findArticlesByTitle(String title);

    /**
     * 根据用户id查询文章
     * @param id
     * @return
     */
    List<Article> findArticlesByUserId(Integer id);

}
