package com.atguigu.survey.component.handler.guest;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.survey.component.service.interfaces.UserService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.utils.GlobaleNames;

@Controller
public class UserHandler {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/guest/user/login", method = RequestMethod.POST)
	public String login(User user, HttpSession session) {
		User loginUser = userService.login(user);
		// session.setAttribute("loginUser", loginUser);
		session.setAttribute(GlobaleNames.LOGIN_USER, loginUser);
		return "redirect:/index.jsp";
	}

	@RequestMapping("guest/user/regist")
	public String regist(User user, Map<String, Object> map) {
		userService.regist(user);

		return "guest/user_login";
	}

	@RequestMapping("/guest/user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.jsp";

	}
}
