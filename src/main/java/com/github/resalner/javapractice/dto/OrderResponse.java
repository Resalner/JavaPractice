package com.github.resalner.javapractice.dto;

import java.util.Date;

public record OrderResponse(
	long id, 
	long userId,
	Date orderDate,
	double totalPrice, 
	String status,
	long addressId,
	String comments
) {
}