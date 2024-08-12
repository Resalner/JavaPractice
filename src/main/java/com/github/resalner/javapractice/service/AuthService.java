package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.dto.JwtAuthorisationData;
import com.github.resalner.javapractice.dto.RefreshTokenData;
import com.github.resalner.javapractice.dto.UserCredentials;
import com.github.resalner.javapractice.dto.RegistrationData;
import com.github.resalner.javapractice.model.User;

public interface AuthService {
	JwtAuthorisationData authentication(UserCredentials userCredentials);

	JwtAuthorisationData refreshToken(RefreshTokenData refreshTokenData);
	
	User registerNewUserAccount(RegistrationData data);
}
