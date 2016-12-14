package com.atguigu.survey.component.handler.manager;

import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.interfaces.AuthService;
import com.atguigu.survey.component.service.interfaces.RoleService;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.exceptions.RemoveRoleFailedException;
import com.atguigu.survey.utils.GlobalMessage;

/**
 * @author shuai xu 2016年10月25日 上午11:19:55
 */
@Controller
public class RoleHandler {
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthService authService;

	/**
	 * 给角色分配权限
	 * 
	 * @param checkAuthIdList
	 * @return
	 */
	@RequestMapping("/manager/role/dispatcherUI")
	public String dispatcherUI(
			@RequestParam(value="checkAuthIdList",required=false) List<Integer> checkAuthIdList,
			@RequestParam("roleId") Integer roleId
			//,HttpSession session
			//,@RequestParam(value="authIdList",required=false)List<Integer> authIdList
			) {
		// 1.先删除已有的角色，权限关联-->中间表
		// 2.再添加进角色，权限关联-->中间表
		//List<Integer> authIdList = (List<Integer>) session.getAttribute("authIdList");
		roleService.dispatcher(roleId, checkAuthIdList);
		return "redirect:/manager/role/showList";
	}

	@RequestMapping("/manager/role/toDispatcherUI/{roleId}")
	public String toDispatcherUI(@PathVariable("roleId") Integer roleId,
			Map<String, Object> map
			//,HttpSession session
			) {
		Role role = roleService.getEntityById(roleId);
		List<Integer> authIdList = roleService.getAuthChecked(roleId);
		List<Auth> authList = authService.getAuthList();
		map.put("authList", authList);
		map.put("role", role);
		map.put("authIdList", authIdList);
		//session.setAttribute("authIdList", authIdList);
		return "manager/role_dispatcherUI";
	}

	@RequestMapping("/manager/role/batchDeleteRole")
	public String batchDeleteRole(
			@RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList) {
		try {
			roleService.batchDeleteRole(roleIdList);
		} catch (Exception e) {
			Throwable cause = e.getCause();
			if (cause != null && cause instanceof BatchUpdateException) {
				throw new RemoveRoleFailedException(
						GlobalMessage.REMOVE_ROLE_FAILED);
			}
		}
		return "redirect:/manager/role/showList";
	}

	@RequestMapping("/manager/role/addUI")
	public String addUI(Role role) {
		roleService.saveEntity(role);
		return "redirect:/manager/role/showList";
	}

	@RequestMapping("/manager/role/showList")
	public String showList(Map<String, Object> map) {
		List<Role> roleList = roleService.getRoleList();
		map.put("roleList", roleList);
		return "manager/role_list";
	}

}
