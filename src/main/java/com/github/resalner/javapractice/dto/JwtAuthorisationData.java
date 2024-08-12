package com.github.resalner.javapractice.dto;

import java.util.List;

public record JwtAuthorisationData(
	String accessToken,
	String refreshToken,
	String username,
	List<String> roles
) {
}
