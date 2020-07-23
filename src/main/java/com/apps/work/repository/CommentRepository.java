package com.apps.work.repository;

import com.apps.work.model.Comment;
import com.apps.work.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
