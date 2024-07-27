package com.github.resalner.javapractice.dto;

public record ProductResponse(long id,
							String name,
							String description,
							double price
) {}