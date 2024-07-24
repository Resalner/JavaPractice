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

import com.github.resalner.javapractice.dto.JwtResponse;
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

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final RefreshTokenService refreshTokenService;
	private final RefreshTokenRepository refreshTokenRepository;
	private final UserDetailsService userDetailsService;

	@Override
	public JwtResponse authentication(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String accessToken = jwtService.generateToken(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

		return new JwtResponse(accessToken, refreshToken.getToken(), userDetails.getUsername(), roles);
	}

	@Override
	public JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String refreshToken = refreshTokenRequest.getToken();

		RefreshToken existingRefreshToken = refreshTokenRepository.findByToken(refreshToken);
		if (existingRefreshToken == null) {
			throw new RuntimeException("Invalid refresh token");
		}

		String username = jwtService.extractUsername(refreshToken);

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		if (!jwtService.validateToken(existingRefreshToken.getToken(), userDetails)) {
			throw new RuntimeException("Invalid refresh token");
		}

		String newAccessToken = jwtService.generateToken(userDetails);
		String newRefreshToken = jwtService.generateToken(userDetails); // Если требуется новый refresh токен

		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return new JwtResponse(newAccessToken, newRefreshToken, username, roles);
	}

}
