package com.github.resalner.javapractice.dto;

public record UserResponse(
	Long id,
	String username,
	String role
) {
}