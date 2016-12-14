package com.atguigu.survey.exceptions;

/**
 * @author shuai xu 2016年10月17日 下午1:27:14
 */
public class UserLoginFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UserLoginFailedException(String message) {
		super(message);
	}
}
