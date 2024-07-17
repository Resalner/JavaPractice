package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.model.UserInfo;

public interface AuthService {
	void registerNewUserAccount(User user, UserInfo userInfo);
}
