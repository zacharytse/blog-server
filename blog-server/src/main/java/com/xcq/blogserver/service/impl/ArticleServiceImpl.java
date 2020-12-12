package com.xcq.blogserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcq.blogserver.constants.ServiceExceptionEnum;
import com.xcq.blogserver.core.exception.ServiceException;
import com.xcq.blogserver.domain.Article;
import com.xcq.blogserver.mapper.ArticleMapper;
import com.xcq.blogserver.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl
        extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void saveArticle(Article article) {
        try {
            articleMapper.insert(article);
            logger.info("[saveArticle][save article {} success]",article.getTitle());
        }catch (Exception e){
            logger.error("[saveArticle][save article {} failure]",article.getTitle());
            throw new ServiceException(ServiceExceptionEnum.ARTICLE_ADD_FAILURE);
        }

    }

    @Override
    public List<Article> findArticlesByTitle(String title) {
        logger.info("[findArticlesByTitle][find all articles with title:{}]",title);
        return articleMapper.findByTitle(title);
    }

    @Override
    public List<Article> findArticlesByUserId(Integer uid) {
        logger.info("[findArticlesByUserId][find all articles by id:{}]",uid);
        return articleMapper.findByUID(uid);
    }
}
