package com.apps.work.repository;

import com.apps.work.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Integer> {
    public Page findBySeoUriAndIdNot(String seoUri, Integer id);
}