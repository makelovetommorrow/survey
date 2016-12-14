package com.atguigu.survey.exceptions;

public class AdminLoginFailedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AdminLoginFailedException(String message) {
		super(message);
	}

}
