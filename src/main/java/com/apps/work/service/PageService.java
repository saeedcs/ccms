package com.apps.work.service;

import com.apps.work.model.Page;
import com.apps.work.model.Todo;
import com.apps.work.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public interface PageService {

    List<Page> getPageList();
}