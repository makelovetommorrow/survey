package com.atguigu.survey.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.survey.log.thread.RequestBinder;

/**
 * @author shuai xu
 * 2016年10月31日 下午6:12:49
 */
public class RequestBinderInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		RequestBinder.bindRequest(request);
		return super.preHandle(request, response, handler);
	}
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		RequestBinder.removeRequest();
	}
}
