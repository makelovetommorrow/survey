package com.atguigu.survey.exceptions;

/**
 * @author shuai xu
 * 2016年10月21日 下午1:20:42
 */
public class BagOrderDuplicateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BagOrderDuplicateException(String message) {
		super(message);
	}

}
