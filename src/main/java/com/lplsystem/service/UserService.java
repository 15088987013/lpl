package com.lplsystem.service;

import com.lplsystem.pojo.User;

public interface UserService {
    User login(String username, String password);
}
