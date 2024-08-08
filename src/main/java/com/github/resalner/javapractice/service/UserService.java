package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.User;

import java.util.List;

public interface UserService {
	List<User> getAllUsers();

	User getUser(long id);

	void deleteUser(long id);

	User updateUser(long id, User user);
}