package com.atguigu.survey.exceptions;

public class AdminNameExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AdminNameExistsException(String message) {
		super(message);
	}

}
