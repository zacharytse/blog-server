package com.xcq.blogserver.service;

import com.xcq.blogserver.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private IArticleService service;

    @Test
    public void testSave() {
        Article article = new Article();
        article.setUid(7);
        article.setArticle("asderewr");
        service.saveArticle(article);
    }

    @Test
    public void testFindByTitle() {
        List<Article> articles = service.findArticlesByTitle("str");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByUID() {
        List<Article> articles = service.findArticlesByUserId(7);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindNewest() {
        Article article = service.findNewestArticle();
        System.out.println(article);
    }
}
