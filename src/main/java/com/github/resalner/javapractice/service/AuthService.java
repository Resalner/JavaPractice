package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.dto.RegistrationData;
import com.github.resalner.javapractice.dto.RegistrationDataResponse;
import com.github.resalner.javapractice.model.User;

public interface AuthService {
	User registerNewUserAccount(RegistrationData data);
}