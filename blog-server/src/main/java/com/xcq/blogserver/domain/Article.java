package com.xcq.blogserver.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 文章的实体类
 */
@TableName("articles")
public class Article implements Serializable {

    @TableId("articleId")
    private Integer articleId;

    @TableField("uid")
    private Integer uid;

    @TableField("title")
    private String title;

    @TableField("article")
    private String article;

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public Article setArticleId(Integer articleId) {
        this.articleId = articleId;
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public Article setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public String getArticle() {
        return article;
    }

    public Article setArticle(String article) {
        this.article = article;
        return this;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                '}';
    }
}
