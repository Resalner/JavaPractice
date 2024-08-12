package com.github.resalner.javapractice.security;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
	private static final String CURRENT_TIMEZONE = "Europe/Minsk"; 
	
	@Value("${jwt.access-token-expiration}")
	private long accessTokenExpiryTime;

	@Value("${jwt.refresh-token-expiration}")
	private long refreshTokenExpiryTime;

	public UserToken createUserToken(String username, String accessToken, String refreshToken) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new EntityNotFoundException("Пользователь не найден: " + username));

		UserToken existingUserToken = userTokenRepository.findByUserId(user.getId());

		UserToken userToken;
		
		ZonedDateTime zonedTimeAccess = Instant.now().plusMillis(accessTokenExpiryTime).atZone(ZoneId.of(CURRENT_TIMEZONE));
		ZonedDateTime zonedTimeRefresh = Instant.now().plusMillis(refreshTokenExpiryTime).atZone(ZoneId.of(CURRENT_TIMEZONE));
				
		if (existingUserToken != null) {
			existingUserToken.setRefreshToken(refreshToken);
			existingUserToken.setRefreshTokenExpiryDate(zonedTimeRefresh);
			existingUserToken.setAccessToken(accessToken);
			existingUserToken.setAccessTokenExpiryDate(zonedTimeAccess);

			userToken = userTokenRepository.save(existingUserToken);

		} else {
			userToken = new UserToken();
			userToken.setUser(user);
			userToken.setRefreshToken(refreshToken);
			userToken.setRefreshTokenExpiryDate(zonedTimeRefresh);
			userToken.setAccessToken(accessToken);
			userToken.setAccessTokenExpiryDate(zonedTimeAccess);

			userToken = userTokenRepository.save(userToken);
		}

		return userToken;
	}

	public UserToken findByRefreshToken(String token) {
		UserToken userToken = userTokenRepository.findByRefreshToken(token);

		if (userToken == null) {
			throw new EntityNotFoundException("Токен обновления не найден");
		}

		return userToken;
	}

	public void setAccessTokenExpiryDate(UserToken existingUserToken) {
		existingUserToken.setAccessTokenExpiryDate(Instant.now().plusMillis(accessTokenExpiryTime).atZone(ZoneId.of(CURRENT_TIMEZONE)));
	}
}
