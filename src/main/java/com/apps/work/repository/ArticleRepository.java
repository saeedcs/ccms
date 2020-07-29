package com.apps.work.repository;

import com.apps.work.model.Article;
import com.apps.work.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
   public Article findBySeoUri(String seoUri);

   public Article findBySeoUriAndIdNot(String seoUri, int parseInt);

   public List<Article> findTop10ByOrderByCreatedOnByDesc();
}
