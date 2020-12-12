package com.xcq.blogcomment.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("comment")
public class Comment implements Serializable {

    //评论的id
    @TableId("id")
    private Integer id;
    //评论的文章的id
    @TableField("articleId")
    private int articleId;
    //对应这层楼的父节点id
    @TableField("parentId")
    private int parentId;
    //被回复者的id名称
    @TableField("targetName")
    private String targetName;
    //回复的内容
    private String content;
    //回复的时间
    private Date date;
    @TableField("targetId")
    private int targetId;

    public int getArticleId() {
        return articleId;
    }

    public Comment setArticleId(int articleId) {
        this.articleId = articleId;
        return this;
    }

    public int getParentId() {
        return parentId;
    }

    public Comment setParentId(int parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getTargetName() {
        return targetName;
    }

    public Comment setTargetName(String targetName) {
        this.targetName = targetName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Comment setDate(Date date) {
        this.date = date;
        return this;
    }

    public int getTargetId() {
        return targetId;
    }

    public Comment setTargetId(int targetId) {
        this.targetId = targetId;
        return this;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", parentId=" + parentId +
                ", targetName='" + targetName + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
