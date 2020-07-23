package com.apps.work.service.impl;

import com.apps.work.model.Article;
import com.apps.work.repository.ArticleRepository;
import com.apps.work.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getArticleList() { return articleRepository.findAll(); }

    @Override
    public Optional<Article> getArticle(Integer id) {
        return articleRepository.findById(id);
    }

    @Override
    public Article findBySeoUri(String seoUri) {
        return articleRepository.findBySeoUri(seoUri);
    }
}
