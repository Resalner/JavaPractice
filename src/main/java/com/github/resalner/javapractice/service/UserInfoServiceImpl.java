package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
	private final UserInfoRepository userInfoRepository;

	@Override
	public List<UserInfo> getUsers() {
		return userInfoRepository.findAll();
	}

	@Override
	public UserInfo getUserInfo(long id) {
		return getUserInfoIfExists(id);
	}

	@Override
	public UserInfo updateUserInfo(long id, UserInfo userInfoForUpdate) {
		UserInfo userInfo = getUserInfoIfExists(id);

		String name = userInfoForUpdate.getName();
		String surname = userInfoForUpdate.getSurname();
		String phoneNumber = userInfoForUpdate.getPhoneNumber();
		Date birthDate = userInfoForUpdate.getBirthDate();
		boolean gender = userInfoForUpdate.isGender();
		String email = userInfoForUpdate.getEmail();

		userInfo.setName(name);
		userInfo.setSurname(surname);
		userInfo.setPhoneNumber(phoneNumber);
		userInfo.setBirthDate(birthDate);
		userInfo.setGender(gender);
		userInfo.setEmail(email);

		userInfo = userInfoRepository.save(userInfo);

		return userInfo;
	}

	private UserInfo getUserInfoIfExists(long id) {
		return userInfoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден пользователь с id = " + id));
	}
}