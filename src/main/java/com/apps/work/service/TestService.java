package com.apps.work.service;

import com.apps.work.model.Role;
import com.apps.work.model.User;
import com.apps.work.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class TestService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    //@PostConstruct
    public void doDbInserts() {
        Role r1 = new Role();
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
        userRepository.save(u1);
    }
}
