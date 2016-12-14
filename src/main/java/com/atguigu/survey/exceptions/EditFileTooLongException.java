package com.atguigu.survey.exceptions;

/**
 * @author shuai xu
 * 2016年10月18日 下午10:56:04
 */
public class EditFileTooLongException extends RuntimeException {

	/**
	 * @param fILE_TOO_LARGE
	 */
	public EditFileTooLongException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
