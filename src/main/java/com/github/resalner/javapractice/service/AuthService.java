package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.dto.JwtAuthorisationData;
import com.github.resalner.javapractice.dto.JwtResponse;
import com.github.resalner.javapractice.dto.RefreshTokenData;
import com.github.resalner.javapractice.dto.UserCredentials;
import com.github.resalner.javapractice.request.LoginRequest;
import com.github.resalner.javapractice.request.RefreshTokenRequest;

public interface AuthService {
	JwtAuthorisationData authentication(UserCredentials userCredentials);

	JwtAuthorisationData refreshToken(RefreshTokenData refreshTokenData);
}
