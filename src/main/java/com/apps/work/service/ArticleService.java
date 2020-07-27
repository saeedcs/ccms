package com.apps.work.service;

import com.apps.work.model.Article;
import com.apps.work.model.Comment;
import com.apps.work.model.Page;

import java.util.*;

public interface ArticleService {

    List<Article> getArticleList();
    Optional<Article> getArticle(Integer id);
    Article findBySeoUri(String seoUri);

    Comment addComment(String comment, String id);
}