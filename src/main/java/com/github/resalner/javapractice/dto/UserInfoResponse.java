package com.github.resalner.javapractice.dto;

import java.util.Date;

public record UserInfoResponse(
	long id,
	String name, 
	String surname,
	String phoneNumber,
	Date birthDate, 
	boolean gender,
	String email
) {
}