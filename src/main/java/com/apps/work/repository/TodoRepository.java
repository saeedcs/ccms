package com.apps.work.repository;

import com.apps.work.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByDescriptionContainingOrTargetDateContaining(String description, Date targetDate);
}
