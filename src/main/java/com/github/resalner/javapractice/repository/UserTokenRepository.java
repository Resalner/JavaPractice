package com.github.resalner.javapractice.repository;

import com.github.resalner.javapractice.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    UserToken findByAccessToken(String accessToken);

    UserToken findByRefreshToken(String refreshToken);

    UserToken findByUsername(String username);
}
