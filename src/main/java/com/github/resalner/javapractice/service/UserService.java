package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.request.UserRequest;
import java.util.List;

public interface UserService{
  List<User> getAllUsers();
  User getUser(long id);
  void addUser(UserRequest userRequest);
  void deleteUser(long id);
  User updateUser(long id, UserRequest userRequest);
}