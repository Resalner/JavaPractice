package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.dto.RegistrationData;
import com.github.resalner.javapractice.dto.RegistrationDataResponse;

public interface AuthService {
	RegistrationDataResponse registerNewUserAccount(RegistrationData data);
}
