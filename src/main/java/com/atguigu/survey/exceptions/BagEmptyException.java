package com.atguigu.survey.exceptions;

/**
 * @author shuai xu
 * 2016年10月21日 上午11:51:07
 */
public class BagEmptyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BagEmptyException(String message) {
		super(message);
	}

}
