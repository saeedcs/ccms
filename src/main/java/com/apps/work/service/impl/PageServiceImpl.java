package com.apps.work.service.impl;

import com.apps.work.model.Page;
import com.apps.work.repository.PageRepository;
import com.apps.work.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("pageService")
public class PageServiceImpl implements PageService {
    @Autowired
    private PageRepository pageRepository;

    @Override
    public List<Page> getPageList() {
        return pageRepository.findAll();
    }

    @Override
    public Optional<Page> getPage(Integer id) {
        return pageRepository.findById(id);
    }
}
