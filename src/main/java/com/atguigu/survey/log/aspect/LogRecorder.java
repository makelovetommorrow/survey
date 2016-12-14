package com.atguigu.survey.log.aspect;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.survey.component.service.interfaces.LogService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Log;
import com.atguigu.survey.log.thread.DataSourceKeyBinder;
import com.atguigu.survey.log.thread.RequestBinder;
import com.atguigu.survey.utils.GlobaleNames;

/**
 * @author shuai xu 2016年10月29日 上午10:08:56
 */
public class LogRecorder {
	@Autowired
	private LogService logService;

	public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable{
		Signature signature =null;
		String methodName =null;
		String typeName =null;
		Object[] args =null;
		List<Object> argList =null;
		Object returnObject =null;
		String paramList =null;
		String exceptionType =null;
		String exceptionMessage =null;
		try {
			signature = joinPoint.getSignature();
			methodName = signature.getName();
			typeName = signature.getDeclaringTypeName();
			args = joinPoint.getArgs();
			argList = Arrays.asList(args);
			paramList = argList.toString();
			returnObject = joinPoint.proceed(args);
		} catch (Throwable e) {
			exceptionType = e.getClass().getName();
			exceptionMessage = e.getMessage();
			Throwable cause = e.getCause();
			while(cause!=null){
				exceptionType=cause.getClass().getName();
				exceptionMessage=cause.getMessage();
				cause=cause.getCause();
			}
			throw e;
		}finally{
			HttpServletRequest request = RequestBinder.getRequest();
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
			Admin admin = (Admin) session.getAttribute(GlobaleNames.LOGIN_ADMIN);
			String userPart=(user==null)?"User未登录":user.getUserName();
			String adminPart=(admin==null)?"Admin未登录":admin.getAdminName();
			String operator=userPart+"/"+adminPart;
			String operatorTime=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
			String returnValue=(returnObject==null)?"[无返回值]":returnObject.toString();
			Log log=new Log(null, operator, operatorTime, methodName, typeName, paramList, returnValue, exceptionType, exceptionMessage);
			DataSourceKeyBinder.bindKey(DataSourceKeyBinder.DATASOURCE_LOG_KEY);
			logService.persistLog(log);
		}
		return returnObject;
	}
}
