package com.apps.work.service.impl;

import com.apps.work.model.ForgetPassword;
import com.apps.work.model.Role;
import com.apps.work.model.User;
import com.apps.work.repository.ForgetRepository;
import com.apps.work.repository.RoleRepository;
import com.apps.work.repository.UserRepository;
import com.apps.work.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ForgetRepository forgetRepository;

    @Override
    public User checkUserExists(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    public User getUser(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public ForgetPassword saveForgetCode(ForgetPassword forgetPassword) {
        return forgetRepository.save(forgetPassword);
    }

    @Override
    public ForgetPassword findByCode(String code) {
        return forgetRepository.findByCode(code);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

}
