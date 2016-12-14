package com.atguigu.survey.exceptions;

/**
 * @author shuai xu
 * 2016年10月28日 下午6:10:14
 */
public class HasNoAuthorityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HasNoAuthorityException(String message) {
		super(message);
	}

}
