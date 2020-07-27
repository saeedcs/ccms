package com.apps.work.service;

import com.apps.work.model.Page;

import java.util.*;

public interface PageService {

    List<Page> getPageList();
    Optional<Page> getPage(Integer id);
    Page findBySeoUriAndIdNot(String seoUri, String id);

    Page findBySeoUri(String seoUri);

    void deletePage(String idStr);

    Page createPage(Page page);
}