package com.ahn.dao;

import com.ahn.entity.User;

import java.util.List;

public interface UserDao {

    public User login(User user);

    List<User> findAll();
}
