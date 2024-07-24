package com.github.resalner.javapractice.security;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.resalner.javapractice.model.RefreshToken;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.RefreshTokenRepository;
import com.github.resalner.javapractice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;

	private final UserRepository userRepository;

	public RefreshToken createRefreshToken(String username) {
		Optional<User> optionalUser = userRepository.findByUsername(username);

		User user = optionalUser
				.orElseThrow(() -> new EntityNotFoundException("Пользователь с таким логином не найден:  " + username));

		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setUser(user);
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setExpiryDate(Instant.now().plusMillis(600000));

		return refreshToken;
	}

	public RefreshToken findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new RuntimeException(
					token.getToken() + " Срок действия токена обновления истек. Перезайдите в аккаунт");
		}
		return token;
	}
}
