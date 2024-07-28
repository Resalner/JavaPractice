package com.github.resalner.javapractice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.resalner.javapractice.dto.JwtAuthorisationData;
import com.github.resalner.javapractice.dto.JwtResponse;
import com.github.resalner.javapractice.dto.RefreshTokenData;
import com.github.resalner.javapractice.dto.UserCredentials;
import com.github.resalner.javapractice.model.RefreshToken;
import com.github.resalner.javapractice.repository.RefreshTokenRepository;
import com.github.resalner.javapractice.repository.UserRepository;
import com.github.resalner.javapractice.request.LoginRequest;
import com.github.resalner.javapractice.request.RefreshTokenRequest;
import com.github.resalner.javapractice.security.JwtService;
import com.github.resalner.javapractice.security.RefreshTokenService;
import com.github.resalner.javapractice.security.details.UserDetailsImpl;
import com.github.resalner.javapractice.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final JwtService jwtService;
	private final RefreshTokenRepository refreshTokenRepository;
	private final UserDetailsService userDetailsService;

	@Override
	public JwtAuthorisationData authentication(UserCredentials userCredentials) {
		boolean isAuthenticated = jwtService.authenticate(userCredentials.login(), userCredentials.password());
		if (!isAuthenticated) {
			throw new RuntimeException("Неверные учетные данные");
		}

		UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(userCredentials.login());
		JwtAuthorisationData jwtAuthData = jwtService.generateJwtAuthData(userDetails);

		return new JwtAuthorisationData(jwtAuthData.accessToken(), jwtAuthData.refreshToken(), jwtAuthData.username(),
				jwtAuthData.roles());
	}

	@Override
	public JwtAuthorisationData refreshToken(RefreshTokenData refreshTokenData) {
		String refreshToken = refreshTokenData.token();

		RefreshToken existingRefreshToken = refreshTokenRepository.findByToken(refreshToken);
		if (existingRefreshToken == null) {
			throw new RuntimeException("Неверный токен обновления");
		}

		String username = jwtService.extractUsername(refreshToken);

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		if (!jwtService.validateToken(existingRefreshToken.getToken(), userDetails)) {
			throw new RuntimeException("Неверный токен обновления");
		}

		String newAccessToken = jwtService.generateToken(userDetails);
		String newRefreshToken = jwtService.generateToken(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return new JwtAuthorisationData(newAccessToken, newRefreshToken, username, roles);
	}

}
