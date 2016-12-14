package com.atguigu.survey.log.thread;

import javax.servlet.http.HttpServletRequest;

/**
 * Thread对象是以local对象为键，以request对象为值保存到一个map中
 * 所以要保证使用的为同一个local，则应该设置为static
 * @author shuai xu 2016年10月29日 上午10:09:28
 */
public class RequestBinder {
	private static ThreadLocal<HttpServletRequest> local = new ThreadLocal<HttpServletRequest>();

	public static void bindRequest(HttpServletRequest request) {
		local.set(request);
	}

	public static void removeRequest() {
		local.remove();
	}

	public static HttpServletRequest getRequest() {
		return local.get();
	}
}
