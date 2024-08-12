package com.github.resalner.javapractice.dto;

public record AddressResponse(
	long id,
	String city,
	String street,
	String houseNumber,
	String apartmentNumber
) {
}