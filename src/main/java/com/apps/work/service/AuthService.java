package com.apps.work.service;

import com.apps.work.model.Role;
import com.apps.work.model.User;

public interface AuthService {
    User checkUserExists(String username);

    User saveUser(User user);

    Role getRoleByName(String name);

    User getUser(String username, String password);
}