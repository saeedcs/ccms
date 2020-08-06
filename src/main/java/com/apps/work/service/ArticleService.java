package com.apps.work.service;

import com.apps.work.model.Article;
import com.apps.work.model.Comment;

import java.io.IOException;
import java.util.*;

public interface ArticleService {

    List<Article> getArticleList();
    Optional<Article> getArticle(Integer id);
    Article findBySeoUri(String seoUri);

    Comment addComment(String comment, String id, Boolean isApproved);

    Article createArticle(Article article) throws IOException;

    Article findBySeoUriAndIdNot(String seoUri, String id);

    void deleteArticle(String idStr);

    List<Comment> getCommentsByArticleId(String articleId);
}