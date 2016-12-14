package com.atguigu.survey.exceptions;

/**
 * @author shuai xu 2016年10月17日 下午7:38:08
 */
public class UserOperationForbidden extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UserOperationForbidden(String message) {
		super(message);
	}
}
