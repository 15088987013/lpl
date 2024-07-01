package com.lplsystem.service.impl;

import com.lplsystem.dao.UserDao;
import com.lplsystem.dao.impl.UserDaoImpl;
import com.lplsystem.pojo.User;
import com.lplsystem.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public User login(String uname, String pwd) {
        return userDao.selectOneUser(uname,pwd);
    }

}
