package com.apps.work.repository;

import com.apps.work.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Integer> {
    public Page findBySeoUriAndIdNot(String seoUri, Integer id);
    public Page findBySeoUri(String seoUri);
    public List<Page> findAllByShowOnMainPage(Boolean showOnMainPage);
}