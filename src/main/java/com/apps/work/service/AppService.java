package com.apps.work.service;

import com.apps.work.model.*;
import com.apps.work.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class AppService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Map<String, String> applicationMap;

    private List<Page> pages;

    private List<String> pageTitles;

    private List<Category> categoryList;

    @PostConstruct
    public void doDbInserts() {
       /* Role r1 = new Role();
        r1.setName("ROLE_ADMIN");
        Role r2 = new Role();
        r2.setName("ROLE_USER");

        User u1 = new User();
        u1.setEnabled(true);
        u1.setUsername("admin");
        u1.setPassword(passwordEncoder.encode("admin"));

        Set<Role> roleSet = new java.util.HashSet<>();
        roleSet.add(r1);
        roleSet.add(r2);

        u1.setRoles(roleSet);
        userRepository.save(u1);*/


        /*Fruit fruit = new Fruit();
        fruit.setTaste("good");
        Page page = new Page();
        page.setId(1);
        fruit.setPage(page);
        fruitRepository.saveAndFlush(fruit);*/

        pages = pageRepository.findAllByShowOnMainPage(true);
        pageTitles = new ArrayList<>();
        for(Page page : pages) {
            pageTitles.add(page.getPageTitle());
        }

        categoryList = categoryRepository.findAll();
    }

    public Map<String, String> getApplicationMap() { return applicationMap; }

    public void setApplicationMap(Map<String, String> applicationMap) { this.applicationMap = applicationMap; }

    public void addApplicationValue(String key, String value) { this.applicationMap.put(key, value); }

    public List<Page> getPages() { return pages; }

    public void setPages(List<Page> pages) { this.pages = pages; }

    public List<String> getPageTitles() {
        return pageTitles;
    }

    public void setPageTitles(List<String> pageTitles) {
        this.pageTitles = pageTitles;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
