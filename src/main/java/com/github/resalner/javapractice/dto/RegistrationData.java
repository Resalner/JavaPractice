package com.github.resalner.javapractice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

import com.github.resalner.javapractice.model.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationData {
	private String username;
	private String password;
	private Role role;
	private String name;
	private String surname;
	private String phoneNumber;
	private Date birthDate;
	private boolean gender;
	private String email;
}
