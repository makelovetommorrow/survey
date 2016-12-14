package com.atguigu.survey.exceptions;

/**
 * @author shuai xu
 * 2016年10月18日 上午10:40:20
 */
public class SaveFileTooLargeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public SaveFileTooLargeException(String message) {
		super(message);
	}
}
