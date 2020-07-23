package com.apps.work.service;

import com.apps.work.model.Page;

import java.util.*;

public interface PageService {

    List<Page> getPageList();
    Optional<Page> getPage(Integer id);
    Page findBySeoUri(String seoUri, String id);
}