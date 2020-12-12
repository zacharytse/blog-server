package com.xcq.blogserver.controller;

import com.xcq.blogserver.constants.ServiceExceptionEnum;
import com.xcq.blogserver.core.vo.CommonResult;
import com.xcq.blogserver.domain.Article;
import com.xcq.blogserver.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提供文章管理的控制类
 */
@RestController
@RequestMapping("/articles")
public class ArticlesController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IArticleService articleService;

    @PostMapping("/add")
    public CommonResult<String> addArticle(Article article) {
        try {
            articleService.saveArticle(article);
            return CommonResult.success("add article success");
        } catch (Exception e) {
            logger.error("[addArticle][addArticle occurred an exception]");
            return CommonResult.error(ServiceExceptionEnum.ARTICLE_ADD_FAILURE.getCode(),
                    ServiceExceptionEnum.ARTICLE_ADD_FAILURE.getMessage());
        }
    }

    @GetMapping("/title")
    public CommonResult<List<Article>> findArticleByTitle(@RequestParam String title) {
        List<Article> articles = articleService.findArticlesByTitle(title);
        return CommonResult.success(articles);
    }

}
