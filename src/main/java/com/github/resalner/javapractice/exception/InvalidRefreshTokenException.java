package com.github.resalner.javapractice.exception;

public class InvalidRefreshTokenException extends RuntimeException{
	public InvalidRefreshTokenException(String message)
	{
		super(message);
	}
}
