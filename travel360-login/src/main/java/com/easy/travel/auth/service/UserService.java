package com.easy.travel.auth.service;

import com.easy.travel.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    void saveAndFlush(User user);
}
