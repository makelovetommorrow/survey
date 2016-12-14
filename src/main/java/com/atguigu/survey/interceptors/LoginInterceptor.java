package com.atguigu.survey.interceptors;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.exceptions.AdminOperationForbiddenException;
import com.atguigu.survey.exceptions.UserOperationForbidden;
import com.atguigu.survey.utils.GlobalMessage;
import com.atguigu.survey.utils.GlobaleNames;

/**
 * 登录拦截器，暂时性的，以后会用权限拦截器
 * 
 * @author shuai xu 2016年10月17日 下午7:18:46
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//1.排除静态资源
		if(handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}
		
		//2.获取当前拦截到的请求要访问的目标地址
		String servletPath = request.getServletPath();
		
		//3.检测当前请求是否是公共资源
		Set<String> publicRes = new HashSet<String>();
		publicRes.add("/guest/user/toRegistUI");
		publicRes.add("/guest/user/toLoginUI");
		publicRes.add("/guest/user/login");
		publicRes.add("/guest/user/regist");
		publicRes.add("/guest/user/logout");
		
		publicRes.add("/manager/admin/toMainUI");
		publicRes.add("/manager/admin/toLoginUI");
		publicRes.add("/manager/admin/login");
		publicRes.add("/manager/admin/logout");
		
		if(publicRes.contains(servletPath)) {
			return true;
		}
		
		HttpSession session = request.getSession();
		
		//4.检查当前名称空间
		if(servletPath.startsWith("/guest")) {
			
			User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
			
			if(user == null) {
				throw new UserOperationForbidden(GlobalMessage.USER_OPERATION_FORBIDDEN);
			}else{
				return true;
			}
			
		}

		if(servletPath.startsWith("/manager")) {
			
			Admin admin = (Admin) session.getAttribute(GlobaleNames.LOGIN_ADMIN);
			
			if(admin == null) {
				throw new AdminOperationForbiddenException(GlobalMessage.ADMIN_OPERATION_FORBIDDEN);
			}else{
				return true;
			}
			
		}
		
		return true;
	}

}

