package com.apps.work.service.impl;

import com.apps.work.model.Article;
import com.apps.work.model.Comment;
import com.apps.work.repository.ArticleRepository;
import com.apps.work.repository.CommentRepository;
import com.apps.work.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private CommentRepository commentRepository;

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

    @Override
    public Comment addComment(String comment, String id) {
        Comment comment1 = new Comment();
        comment1.setCommentText(comment);
        Article article = new Article();
        article.setId(Integer.parseInt(id));
        comment1.setArticle(article);
        return commentRepository.save(comment1);
    }

}
