package com.atguigu.survey.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.survey.component.service.interfaces.ResService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.exceptions.AdminOperationForbiddenException;
import com.atguigu.survey.exceptions.HasNoAuthorityException;
import com.atguigu.survey.exceptions.UserOperationForbidden;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobalMessage;
import com.atguigu.survey.utils.GlobaleNames;

/**
 * @author shuai xu 2016年10月28日 下午6:02:47
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private ResService resService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}
		HttpSession session = request.getSession();
		String servletPath = request.getServletPath();
		servletPath = DataprocessUtils.cutServletPath(servletPath);
		Res res = resService.getResByServletPath(servletPath);
		boolean status = res.isPublicStatus();
		if (status) {
			return true;
		}
		if (servletPath.startsWith("/guest")) {
			User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
			if (user == null) {
				throw new UserOperationForbidden(
						GlobalMessage.USER_OPERATION_FORBIDDEN);
			}
			String codeArrStr = user.getCodeArrStr();
			boolean hasAuthority = DataprocessUtils.checkAuthority(codeArrStr,
					res);
			if (hasAuthority) {
				return true;
			} else {
				throw new HasNoAuthorityException(
						GlobalMessage.HAS_NO_AUTHORITY);
			}
		}

		if (servletPath.startsWith("/manager")) {
			Admin admin = (Admin) session
					.getAttribute(GlobaleNames.LOGIN_ADMIN);
			if (admin == null) {
				throw new AdminOperationForbiddenException(
						GlobalMessage.ADMIN_OPERATION_FORBIDDEN);
			}
			String adminName = admin.getAdminName();
			if ("SuperAdmin".equals(adminName)) {
				return true;
			}
			String codeArrStr = admin.getCodeArrStr();
			boolean hasAuthority = DataprocessUtils.checkAuthority(codeArrStr,
					res);
			if (hasAuthority) {
				return true;
			} else {
				throw new HasNoAuthorityException(
						GlobalMessage.HAS_NO_AUTHORITY);
			}
		}
		return true;
	}
}
