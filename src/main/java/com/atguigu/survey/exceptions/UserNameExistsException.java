package com.atguigu.survey.exceptions;

/**
 * @author shuai xu 2016年10月17日 下午6:03:02
 */
public class UserNameExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UserNameExistsException(String message) {
		super(message);
	}
}
