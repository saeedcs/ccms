package com.apps.work.service.impl;

import com.apps.work.model.Article;
import com.apps.work.model.Comment;
import com.apps.work.repository.ArticleRepository;
import com.apps.work.repository.CommentRepository;
import com.apps.work.service.ArticleService;
import com.apps.work.util.CcmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
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
    public Article findBySeoUriAndIdNot(String seoUri, String id) {
        return articleRepository.findBySeoUriAndIdNot(seoUri, Integer.parseInt(id));
    }

    @Override
    public void deleteArticle(String idStr) {
        Integer id = Integer.parseInt(idStr);
        articleRepository.delete(articleRepository.getOne(id));
    }

    @Override
    public Article createArticle(Article article) throws IOException {
        boolean add = false;
        if(article.getId() == null) {
            add = true;
        }
        if(add) article.setCreatedOn(new Date(Calendar.getInstance().getTimeInMillis()));
        else {
            article.setChangedOn(new Date(Calendar.getInstance().getTimeInMillis()));
            Article article2 = articleRepository.getOne(article.getId());
            article.setCreatedBy(article2.getCreatedBy());
            article.setCreatedOn(article2.getCreatedOn());
            article.setAuthor(article2.getAuthor());
        }
        Article article1 = articleRepository.save(article);
        if(article1.getAuthor() == null) {
            article1.setAuthor("");
        }
        if(add) {
            CcmsUtil.makeSearchIndex(article1.getId() + "", article1.getSeoUri(), article1.getArticleTitle(),
                    article1.getArticleBody(), article1.getAuthor(), article1.getCreatedOn().toString());
        } else {
            CcmsUtil.updateSearchIndex(article1.getId() + "", article1.getSeoUri(), article1.getArticleTitle(),
                    article1.getArticleBody(), article1.getAuthor(), article1.getCreatedOn().toString());
        }
        return article1;
    }

    @Override
    public Comment addComment(String comment, String id, Boolean isApproved) {
        Comment comment1 = new Comment();
        comment1.setCommentText(comment);
        comment1.setApproved(isApproved);
        Article article = new Article();
        article.setId(Integer.parseInt(id));
        comment1.setArticle(article);
        return commentRepository.save(comment1);
    }
    @Override
    public List<Comment> getCommentsByArticleId(String articleId) {
        return commentRepository.findAllByArticle_Id(Integer.parseInt(articleId));
    }
}
