package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(long id);

    User saveUser(User user);

    void deleteUser(long id);

    User updateUser(long id, User user);
}