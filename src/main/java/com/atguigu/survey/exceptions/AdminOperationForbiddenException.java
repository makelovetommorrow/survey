package com.atguigu.survey.exceptions;

public class AdminOperationForbiddenException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AdminOperationForbiddenException(String message) {
		super(message);
	}

}
