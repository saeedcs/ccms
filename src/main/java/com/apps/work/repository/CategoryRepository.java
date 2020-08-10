package com.apps.work.repository;

import com.apps.work.model.Category;
import com.apps.work.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {

    public List<Category> findAllByShowOnMainPage(Boolean showOnMainPage);
}
