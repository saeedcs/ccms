package com.apps.work.service;

import com.apps.work.model.*;
import com.apps.work.repository.ApplicationRepository;
import com.apps.work.repository.FruitRepository;
import com.apps.work.repository.PageRepository;
import com.apps.work.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class AppService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PageRepository pageRepository;

    private Map<String, String> applicationMap;

    private List<Page> pages;

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
        applicationMap = new HashMap<>();
        List<Application> applicationList = applicationRepository.findAll();
        for(Application application : applicationList) {
            applicationMap.put(application.getKeyPair(), application.getValuePair());
        }

        pages = pageRepository.findAllByShowOnMainPage(true);
    }

    public Map<String, String> getApplicationMap() {
        return applicationMap;
    }

    public void setApplicationMap(Map<String, String> applicationMap) {
        this.applicationMap = applicationMap;
    }

    public void addApplicationValue(String key, String value) {
        this.applicationMap.put(key, value);
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }


}
