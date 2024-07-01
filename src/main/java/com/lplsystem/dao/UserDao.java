package com.lplsystem.dao;

import com.lplsystem.pojo.User;

public interface UserDao {
    User selectOneUser(String uname, String pwd);
}
