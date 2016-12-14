package com.atguigu.survey.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.survey.component.service.interfaces.ResService;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.utils.DataprocessUtils;

/**
 * @author shuai xu 2016年10月25日 下午2:05:41
 */
public class ResGenerateInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private ResService resService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}
		
		String servletPath = request.getServletPath();
		servletPath = DataprocessUtils.cutServletPath(servletPath);
		boolean exists = resService.checkServletPathExists(servletPath);
		if (exists) {
			return true;
		}
		int systemMaxCode = 1 << 30;
		Integer resPos = null;
		Integer resCode = null;
		Integer maxPos = resService.getMaxResPos();
		Integer maxCode = (maxPos == null) ? null : resService
				.getMaxResCode(maxPos);
		if (maxPos == null) {
			resCode = 1;
			resPos = 0;
		}
		if (maxPos != null && maxCode == systemMaxCode) {
			resCode = 1;
			resPos = maxPos + 1;
		}
		if (maxPos != null && maxCode < systemMaxCode) {
			resCode = maxCode << 1;
			resPos = maxPos;
		}

		Res res = new Res(null, servletPath, resCode, resPos, false);
		resService.saveEntity(res);
		return true;
	}
}
