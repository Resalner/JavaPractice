package com.github.resalner.javapractice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDataResponse {
	private long id;
	private String username;
	private String role;
	private String name;
	private String surname;
	private String phoneNumber;
	private Date birthDate;
	private boolean gender;
	private String email;
}
