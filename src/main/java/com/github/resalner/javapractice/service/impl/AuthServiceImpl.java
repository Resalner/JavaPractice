package com.github.resalner.javapractice.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.resalner.javapractice.dto.RegistrationData;
import com.github.resalner.javapractice.dto.RegistrationDataResponse;
import com.github.resalner.javapractice.exception.EntityAlreadyExistsException;
import com.github.resalner.javapractice.map.UserRegistrationMapper;
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
	private final UserRegistrationMapper registrationMapper;

	@Override
	public User registerNewUserAccount(RegistrationData data) {
		if (usernameExists(data.getUsername())) {
			throw new EntityAlreadyExistsException("Аккаунт с таким логином уже есть: " + data.getUsername());
		}

		User user = registrationMapper.toUser(data);
		UserInfo userInfo = registrationMapper.toUserInfo(data);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setInfo(userInfo);
		userInfo.setUser(user);

		user.setNew(true);
		userInfo.setNew(true);

		userRepository.save(user);
		userInfoRepository.save(userInfo);

		return user;
	}

	private boolean usernameExists(String username) {
		return userRepository.findByUsername(username) != null;
	}
}