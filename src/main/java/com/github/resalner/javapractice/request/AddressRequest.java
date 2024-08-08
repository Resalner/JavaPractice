package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(

		@NotBlank(message = "Необходимо указать город")
		String city,

		@NotBlank(message = "Необходимо указать улицу")
		String street,

		@NotBlank(message = "Необходимо указать номер дома")
		String houseNumber,

		String apartmentNumber
){}