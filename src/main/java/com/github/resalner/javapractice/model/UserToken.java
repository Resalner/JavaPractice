package com.github.resalner.javapractice.model;

import java.time.Instant;
import java.time.ZonedDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_tokens")
@Data
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_token", nullable = false, unique = true)
    private String accessToken;

    @Column(name = "access_token_expiration", nullable = false)
    private ZonedDateTime accessTokenExpiryDate;

    @Column(name = "refresh_token", nullable = false, unique = true)
    private String refreshToken;

    @Column(name = "refresh_token_expiration", nullable = false)
    private ZonedDateTime refreshTokenExpiryDate;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
