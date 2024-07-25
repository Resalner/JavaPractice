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
		return userInfoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден пользователь с id = " + id));
	}

	@Override
	public void deleteUserInfo(long id) {
		userInfoRepository.deleteById(id);
	}

	@Override
	public UserInfo updateUserInfo(long id, UserInfo userInfoForUpdate) {
		UserInfo userInfo = userInfoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден пользователь с id = " + id));

		String name = userInfoForUpdate.getName();
		String surname = userInfoForUpdate.getSurname();
		String phoneNumber = userInfoForUpdate.getPhoneNumber();
		Date birthDate = userInfoForUpdate.getBirthDate();
		boolean gender = userInfoForUpdate.isGender();
		String email = userInfoForUpdate.getEmail();

		if (Objects.nonNull(name) && !"".equals(name)) {

			userInfo.setName(name);
		}
		if (Objects.nonNull(surname) && !"".equals(surname)) {

			userInfo.setSurname(surname);
		}
		if (Objects.nonNull(phoneNumber) && !"".equals(phoneNumber)) {

			userInfo.setPhoneNumber(phoneNumber);
		}
		if (Objects.nonNull(birthDate)) {

			userInfo.setBirthDate(birthDate);
		}
		if (Objects.nonNull(gender)) {

			userInfo.setGender(gender);
		}
		if (Objects.nonNull(email) && !"".equals(email)) {

			userInfo.setEmail(email);
		}
		userInfo = userInfoRepository.save(userInfo);
		return userInfo;
	}
}