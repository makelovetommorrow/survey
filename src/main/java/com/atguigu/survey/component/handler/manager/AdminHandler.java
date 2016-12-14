package com.atguigu.survey.component.handler.manager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.interfaces.AdminService;
import com.atguigu.survey.component.service.interfaces.RoleService;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.GlobaleNames;

@Controller
public class AdminHandler {

	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;

	@RequestMapping("/manager/admin/dispatcher")
	public String dispatcher(
			@RequestParam(value = "newRoleIdList", required = false) List<Integer> newRoleIdList,
			@RequestParam("adminId") Integer adminId) {
		adminService.dispatcher(adminId, newRoleIdList);
		return "redirect:/manager/admin/showList";
	}

	@RequestMapping("/manager/admin/toDispatcherUI/{adminId}")
	public String toDispatcher(@PathVariable("adminId") Integer adminId,
			Map<String, Object> map) {
		List<Role> roleList = roleService.getRoleList();
		List<Integer> roleIdList = adminService.getRoleIdByAdminId(adminId);
		map.put("roleIdList", roleIdList);
		map.put("roleList", roleList);
		map.put("adminId", adminId);
		return "manager/admin_dispatcher";
	}

	@RequestMapping("/manager/admin/addUI")
	public String addUI(Admin admin) {
		adminService.regist(admin);
		return "redirect:/manager/admin/showList";
	}

	@RequestMapping("/manager/admin/showList")
	public String showList(Map<String, Object> map) {
		List<Admin> adminList = adminService.getAdminList();
		map.put("adminList", adminList);
		return "manager/admin_list";
	}

	@RequestMapping("/manager/admin/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/manager/admin/toMainUI";
	}

	@RequestMapping("/manager/admin/login")
	public String login(Admin admin, HttpSession session) {

		Admin adminLogin = adminService.login(admin);

		session.setAttribute(GlobaleNames.LOGIN_ADMIN, adminLogin);

		return "redirect:/manager/admin/toMainUI";
	}

}
