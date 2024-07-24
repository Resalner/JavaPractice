package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.dto.JwtResponse;
import com.github.resalner.javapractice.request.LoginRequest;
import com.github.resalner.javapractice.request.RefreshTokenRequest;

public interface AuthService {
	JwtResponse authentication(LoginRequest loginRequest);

	JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
