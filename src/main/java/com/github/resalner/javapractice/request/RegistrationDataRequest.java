package com.github.resalner.javapractice.request;

import java.util.Date;

import com.github.resalner.javapractice.annotation.PasswordMatches;
import com.github.resalner.javapractice.annotation.RoleAnnotation;
import com.github.resalner.javapractice.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class RegistrationDataRequest {

	@NotBlank(message = "Заполните поле логин")
	@Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя может содержать только буквы")
	String username;

	@NotBlank(message = "Заполните поле пароль")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
			message = "Пароль должен содержать хотя бы одну букву в верхнем и нижнем регистре и одну цифру")
	String password;

	private String matchingPassword;
	
	@RoleAnnotation
	Role role;

	@NotBlank(message = "Заполните поле имя")
	@Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя может содержать только буквы")
	private String name;

	@NotBlank(message = "Заполните поле фамилия")
	@Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя может содержать только буквы")
	private String surname;

	@NotBlank(message = "Заполните поле номер телефона")
	@Pattern(regexp = "^375(33|44|29|25)\\d{7}$", message = "неверный формат номера телефона")
	private String phoneNumber;

	@Past(message = "Дата рождения должна быть в прошлом")
	private Date birthDate;

	private boolean gender;

	@NotBlank(message = "Заполните поле email")
	@Email(message = "Необходимо указать корректный email")
	private String email;
}