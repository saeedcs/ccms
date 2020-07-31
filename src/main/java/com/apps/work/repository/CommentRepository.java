package com.apps.work.repository;

import com.apps.work.model.Article;
import com.apps.work.model.Comment;
import com.apps.work.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findAllByArticle_Id(Integer articleId);
}
