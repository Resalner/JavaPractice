package com.github.resalner.javapractice.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.resalner.javapractice.exception.EntityAlreadyExistsException;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.repository.UserInfoRepository;
import com.github.resalner.javapractice.repository.UserRepository;
import com.github.resalner.javapractice.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final UserInfoRepository userInfoRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public void registerNewUserAccount(User user, UserInfo userInfo) {
		if (usernameExists(user.getUsername())) {
			throw new EntityAlreadyExistsException("Аккаунт с таким логином уже есть: " + user.getUsername());
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setNew(true);
		userInfo.setNew(true);

		userRepository.save(user);
		userInfoRepository.save(userInfo);
	}

	private boolean usernameExists(String username) {
		return userRepository.findByUsername(username) != null;
	}
}
