package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.repository.UserInfoRepository;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
	private final UserInfoRepository userInfoRepository;

	@Override
	public List<UserInfo> getUsers() {
		return userInfoRepository.findAll();
	}

	@Override
	public UserInfo saveUserInfo(UserInfo userInfo) {
		userInfo = userInfoRepository.save(userInfo);
		return userInfo;
	}

	@Override
	public UserInfo getUserInfo(long id) {
		return getUserInfoIfExists(id);
	}

	@Override
	public void deleteUserInfo(long id) {
		if (!userInfoRepository.existsById(id)) {
			throw new EntityNotFoundException("Не найден продукт с id = " + id);
		}
		userInfoRepository.deleteById(id);
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
		UserInfo userInfo = userInfoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден пользователь с id = " + id));

		return userInfo;
	}
}