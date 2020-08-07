package com.apps.work.repository;

import com.apps.work.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findAllByArticle_Id(Integer articleId);
    public List<Comment> findAllByIsApproved(boolean isApproved);
}
