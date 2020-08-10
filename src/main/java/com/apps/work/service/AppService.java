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

    @Autowired
    private SubscribedUserRepository subscribedUserRepository;

    private Map<String, String> applicationMap;

    private List<Page> pages;

    private List<Page> pageTitles;

    private List<Category> categoryList;

    @Autowired
    private CommentRepository commentRepository;

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
            pageTitles.add(page);
        }

        categoryList = categoryRepository.findAll();
    }

    public Map<String, String> getApplicationMap() { return applicationMap; }

    public void setApplicationMap(Map<String, String> applicationMap) { this.applicationMap = applicationMap; }

    public void addApplicationValue(String key, String value) { this.applicationMap.put(key, value); }

    public List<Page> getPages() { return pages; }

    public void setPages(List<Page> pages) { this.pages = pages; }

    public List<Page> getPageTitles() {
        return pageTitles;
    }

    public void setPageTitles(List<Page> pageTitles) {
        this.pageTitles = pageTitles;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }


    public List<Comment> getCommentsByIsApproved(Boolean isApproved) {
        return commentRepository.findAllByIsApproved(isApproved);
    }

    public Comment approveComment(String commentId, Boolean isApproved) {
        Comment comment1 = new Comment();
        comment1.setId(Integer.parseInt(commentId));
        comment1.setApproved(isApproved);
//        Article article = new Article();
//        article.setId(Integer.parseInt(id));
//        comment1.setArticle(article);
        return commentRepository.save(comment1);
    }

    public SubscribedUser userSubscribe(String email) {
        SubscribedUser subscribedUser = new SubscribedUser();
        subscribedUser.setEmail(email);
        return subscribedUserRepository.save(subscribedUser);
    }
}