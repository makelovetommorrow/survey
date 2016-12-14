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
import com.atguigu.survey.component.service.interfaces.ResService;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.exceptions.RemoveAuthListFailedException;
import com.atguigu.survey.utils.GlobalMessage;

/**
 * @author shuai xu 2016年10月25日 上午11:19:14
 */
@Controller
public class AuthHandler {
	@Autowired
	private ResService resService;
	@Autowired
	private AuthService authService;
	@RequestMapping("/manager/auth/dispatcher")
	public String dispatcher(@RequestParam("authId")Integer authId
			,@RequestParam(value
					="newResCheckedList",required=false)List<Integer> resList){
		authService.dispatcher(authId,resList);
		return "redirect:/manager/auth/showList";
	}
	@RequestMapping("/manager/auth/toDispatcherUI/{authId}")
	public String toDispatcher(
			@PathVariable("authId")Integer authId
			,Map<String,Object> map){
		List<Res> resList = resService.getResList();
		map.put("resList", resList);
		List<Integer> resIdList=authService.getOldResByAuthId(authId);
		map.put("resIdList", resIdList);
		map.put("authId", authId);
		return "manager/auth_dispatcher";
	}
	@RequestMapping("/manager/auth/batchDeleteAuth")
	public String batchDeleteAuth(
			@RequestParam(value = "delAuthIdList", required = false) List<Integer> authIdList) {
		if (authIdList != null && authIdList.size() > 0) {
			try {
				authService.batchDeleteAuth(authIdList);
			} catch (Exception e) {
				Throwable cause = e.getCause();
				if (cause != null && cause instanceof BatchUpdateException) {
					throw new RemoveAuthListFailedException(
							GlobalMessage.REMOVE_AUTH_FAILED);
				}
			}

		}
		return "redirect:/manager/auth/showList";
	}

	@RequestMapping("/manager/auth/addUI")
	public String addUI(Auth auth) {
		authService.saveEntity(auth);
		return "redirect:/manager/auth/showList";
	}

	@RequestMapping("/manager/auth/showList")
	public String showList(Map<String, Object> map) {
		List<Auth> authList = authService.getAuthList();
		map.put("authList", authList);
		return "manager/auth_showList";
	}
}
