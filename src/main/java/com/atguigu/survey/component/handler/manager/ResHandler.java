package com.atguigu.survey.component.handler.manager;

import java.sql.BatchUpdateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.survey.component.service.interfaces.ResService;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.exceptions.RemoveResListFailedException;
import com.atguigu.survey.utils.GlobalMessage;

/**
 * @author shuai xu 2016年10月25日 上午11:18:27
 */
@Controller
public class ResHandler {
	@Autowired
	private ResService resService;

	@ResponseBody
	@RequestMapping("/manager/res/toggleStatus")
	public Map<String, String> toggleStatus(@RequestParam("resId") Integer resId) {
		System.out.println("resId:" + resId);
		Map<String, String> jsonMap = new HashMap<String, String>();
		try {
			boolean resultStatus = resService.updateStatus(resId);
			jsonMap.put("resultStatus", resultStatus + "");
			jsonMap.put("message", "success");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("message", "failed");
		}
		// {"resultStatus":resultStatus,"message":""}

		return jsonMap;
	}

	@RequestMapping("/manager/res/batchDeleteRes")
	public String batchDeleteRes(
			@RequestParam(value = "delResList", required = false) List<Integer> resIdList) {
		if (resIdList != null && resIdList.size() > 0) {
			try {
				resService.batchDeleteRes(resIdList);
			} catch (Exception e) {
				Throwable cause = e.getCause();
				if (cause != null && cause instanceof BatchUpdateException) {
					throw new RemoveResListFailedException(
							GlobalMessage.REMOVE_RES_FAILED);
				}
			}

		}
		return "redirect:/manager/res/showList";
	}

	@RequestMapping("/manager/res/showList")
	public String showList(Map<String, Object> map) {
		List<Res> list = resService.getResList();
		map.put("list", list);
		return "manager/res_list";
	}
}
