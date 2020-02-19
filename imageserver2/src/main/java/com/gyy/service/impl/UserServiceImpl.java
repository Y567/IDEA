package com.gyy.service.impl;

import com.gyy.dao.UserDao;
import com.gyy.dao.impl.UserDaoImpl;
import com.gyy.domain.User;
import com.gyy.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void activeUser(int id) {
        userDao.activeUser(id);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email,password);
    }
}
