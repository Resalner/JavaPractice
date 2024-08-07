package com.github.resalner.javapractice.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.resalner.javapractice.dto.JwtAuthorisationData;
import com.github.resalner.javapractice.dto.RefreshTokenData;
import com.github.resalner.javapractice.dto.UserCredentials;
import com.github.resalner.javapractice.dto.RegistrationData;
import com.github.resalner.javapractice.exception.EntityAlreadyExistsException;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.map.UserRegistrationMapper;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.model.UserToken;
import com.github.resalner.javapractice.repository.UserRepository;
import com.github.resalner.javapractice.repository.UserInfoRepository;
import com.github.resalner.javapractice.repository.UserTokenRepository;
import com.github.resalner.javapractice.security.JwtService;
import com.github.resalner.javapractice.security.UserTokenService;
import com.github.resalner.javapractice.security.details.UserDetailsImpl;
import com.github.resalner.javapractice.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final JwtService jwtService;
	private final UserTokenRepository userTokenRepository;
	private final UserTokenService userTokenService;
	private final UserDetailsService userDetailsService;
	private final UserRepository userRepository;
	private final UserInfoRepository userInfoRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final UserRegistrationMapper registrationMapper;

	@Override
	public JwtAuthorisationData authentication(UserCredentials userCredentials) {
		boolean isAuthenticated = jwtService.authenticate(userCredentials.login(), userCredentials.password());
		if (!isAuthenticated) {
			throw new RuntimeException("Неверные учетные данные");
		}

		UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(userCredentials.login());

		JwtAuthorisationData jwtAuthData = jwtService.generateJwtAuthData(userDetails);

		return jwtAuthData;
	}

	@Override
    public JwtAuthorisationData refreshToken(RefreshTokenData refreshTokenData) {
        String refreshToken = refreshTokenData.refreshToken(); 

        UserToken existingUserToken = userTokenService.findByRefreshToken(refreshToken); 
        
        User user = userRepository.findById(existingUserToken.getId())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден: " + existingUserToken.getId())); 
        
        String username =  user.getUsername();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username); 

        if (!jwtService.validateRefreshToken(refreshToken, username, user)) {
            throw new RuntimeException("Неверный токен обновления");
        }

        String newAccessToken = jwtService.generateAccessToken(userDetails);
        existingUserToken.setAccessToken(newAccessToken);
        userTokenService.setAccessTokenExpiryDate(existingUserToken); 
        userTokenRepository.save(existingUserToken);
        
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtAuthorisationData(newAccessToken, refreshToken, username, roles);
    }

	@Override
	public User registerNewUserAccount(RegistrationData data) {
		if (usernameExists(data.getUsername())) {
			throw new EntityAlreadyExistsException("Аккаунт с таким логином уже есть: " + data.getUsername());
		}

		User user = registrationMapper.toUser(data);
		UserInfo userInfo = registrationMapper.toUserInfo(data);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setInfo(userInfo);
		userInfo.setUser(user);

		user.setNew(true);
		userInfo.setNew(true);

		userRepository.save(user);
		userInfoRepository.save(userInfo);

		return user;
	}

	private boolean usernameExists(String username) {
		return userRepository.findByUsername(username).isPresent();
	}
}
