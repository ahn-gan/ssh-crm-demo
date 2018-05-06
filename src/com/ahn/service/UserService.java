package com.ahn.service;

import com.ahn.dao.UserDao;
import com.ahn.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//��������
@Transactional
public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(User user) {
        return userDao.login(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
