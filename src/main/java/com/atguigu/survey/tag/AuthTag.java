package com.atguigu.survey.tag;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.survey.component.service.interfaces.ResService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobaleNames;

/**
 * @author shuai xu
 * 2016年10月28日 下午4:46:51
 */
public class AuthTag extends SimpleTagSupport{
	private String servletPath;

	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	/**
	 * 1.先为其做准备：   获取session通过pageContext，再获取IOC通过servletContext；
	 * 
	 */
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		HttpSession session = pageContext.getSession();
		ServletContext servletContext = pageContext.getServletContext();
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		ResService resService = ioc.getBean(ResService.class);
		Res res=resService.getResByServletPath(servletPath);
		boolean status = res.isPublicStatus();
		if(status){
			//公共资源直接执行标签体
			getJspBody().invoke(null);
			return ;
		}
		//非公共资源先判断其名称空间，若为用户则执行下列操作
		if(servletPath.startsWith("/guest")){
			User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
			if(user!=null){
				String codeArrStr=user.getCodeArrStr();
				boolean hasAuthority=DataprocessUtils.checkAuthority(codeArrStr,res);
				if(hasAuthority){
					getJspBody().invoke(null);
					return ;
				}
			}
		}
		//若为管理员则执行下列操作
		if(servletPath.startsWith("/manager")){
			Admin admin = (Admin) session.getAttribute(GlobaleNames.LOGIN_ADMIN);
			String adminName = admin.getAdminName();
			if("SuperAdmin".equals(adminName)){
				getJspBody().invoke(null);
				return;
			}
			String codeArrStr = admin.getCodeArrStr();
			boolean hasAuthority = DataprocessUtils.checkAuthority(codeArrStr, res);
			if(hasAuthority){
				getJspBody().invoke(null);
				return ;
			}
		}
	}
}
