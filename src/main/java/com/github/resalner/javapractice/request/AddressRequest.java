package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(

		@NotBlank(message = "Заполните поле город")
		String city,

		@NotBlank(message = "Заполните поле улица")
		String street,

		@NotBlank(message = "Заполните поле номер дома")
		String houseNumber,

		String apartmentNumber
){}