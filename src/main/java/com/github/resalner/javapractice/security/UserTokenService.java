package com.github.resalner.javapractice.security;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.model.UserToken;
import com.github.resalner.javapractice.repository.UserRepository;
import com.github.resalner.javapractice.repository.UserTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserTokenService {

	private final UserTokenRepository userTokenRepository;
	private final UserRepository userRepository;

	@Value("${jwt.access-token-expiration}")
	private long accessTokenExpiryTime;

	@Value("${jwt.refresh-token-expiration}")
	private long refreshTokenExpiryTime;

	public UserToken createUserToken(String username, String accessToken) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new EntityNotFoundException("Пользователь не найден: " + username));

		UserToken existingUserToken = userTokenRepository.findByUsername(username);

		UserToken userToken;

		if (existingUserToken != null) {
			existingUserToken.setRefreshToken(UUID.randomUUID().toString());
			existingUserToken.setRefreshTokenExpiryDate(Instant.now().plusMillis(refreshTokenExpiryTime));
			existingUserToken.setAccessToken(accessToken);
			existingUserToken.setAccessTokenExpiryDate(Instant.now().plusMillis(accessTokenExpiryTime)); 

			userToken = userTokenRepository.save(existingUserToken);
		} else {
			userToken = new UserToken();
			userToken.setUsername(username);
			userToken.setRefreshToken(UUID.randomUUID().toString());
			userToken.setRefreshTokenExpiryDate(Instant.now().plusMillis(refreshTokenExpiryTime));
			userToken.setAccessToken(accessToken);
			userToken.setAccessTokenExpiryDate(Instant.now().plusMillis(accessTokenExpiryTime)); 
			
			userToken = userTokenRepository.save(userToken);
		}

		return userToken;
	}

	public UserToken findByRefreshToken(String token) {
		UserToken userToken = userTokenRepository.findByRefreshToken(token);

		if (userToken == null) {
			throw new RuntimeException("Токен обновления не найден");
		}

		return userToken;
	}

	public UserToken verifyExpiration(UserToken token) {
		if (token.getRefreshTokenExpiryDate().isBefore(Instant.now())) {
			userTokenRepository.delete(token);
			throw new RuntimeException("Refresh token expired. Please log in again.");
		}
		return token;
	}
}
