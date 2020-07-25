package com.apps.work.service;

import com.apps.work.model.ForgetPassword;
import com.apps.work.model.Role;
import com.apps.work.model.User;

public interface AuthService {
    User checkUserExists(String username);

    User saveUser(User user);

    Role getRoleByName(String name);

    User getUser(String username, String password);

    ForgetPassword saveForgetCode(ForgetPassword forgetPassword);

    ForgetPassword findByCode(String code);

    User getUserByUsername(String username);
}