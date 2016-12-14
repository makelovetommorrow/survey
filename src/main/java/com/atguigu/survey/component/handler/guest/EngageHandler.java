package com.atguigu.survey.component.handler.guest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.interfaces.EngageService;
import com.atguigu.survey.component.service.interfaces.SurveyService;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.model.Page;
import com.atguigu.survey.utils.GlobaleNames;

/**
 * @author shuai xu 2016年10月21日 下午8:21:12
 */
@SuppressWarnings("all")
@Controller
public class EngageHandler {
	@Autowired
	private EngageService engageService;
	@Autowired
	private SurveyService surveyService;

	@RequestMapping("/guest/engage/engage")
	public String engage(
			@RequestParam("currentIndex") Integer currentIndex,
			@RequestParam("currentBagId") Integer currentBagId,
			HttpServletRequest request, HttpSession session) {

		// 从Session域中获取allBagMap
		Map<Integer, Map<String, String[]>> allBagMap = (Map<Integer, Map<String, String[]>>) session
				.getAttribute(GlobaleNames.ALL_BAG_MAP);

		// 从request对象中获取“封装所有请求参数”的Map
		Map<String, String[]> paramMap = request.getParameterMap();

		// 创建新的Map对象用来保存paramMap中的数据
		Map<String, String[]> myParamMap = new HashMap<String, String[]>(
				paramMap);

		// 将myParamMap以bagId为键保存到allBagMap中
		allBagMap.put(currentBagId, myParamMap);

		if (paramMap.containsKey("submit_next")
				|| paramMap.containsKey("submit_prev")) {

			// 从Session域中获取bagList
			List<Bag> bagList = (List<Bag>) session
					.getAttribute(GlobaleNames.BAGS);
			System.err.println("bags" + bagList);
			// 根据旧的索引值计算新的索引值
			Integer newIndex = null;

			if (paramMap.containsKey("submit_next")) {
				newIndex = currentIndex + 1;
			}

			if (paramMap.containsKey("submit_prev")) {
				newIndex = currentIndex - 1;
			}

			// 根据新的索引值从bagList中获取Bag对象
			Bag bag = bagList.get(newIndex);
			System.err.println("bag---" + bag);
			// 将bag对象保存到请求域中
			request.setAttribute(GlobaleNames.CURRENT_BAG, bag);

			// 将计算得到的新的索引值保存到请求域中
			request.setAttribute(GlobaleNames.CURRENT_INDEX, newIndex);

			return "guest/engage_engage";
		}

		if (paramMap.containsKey("submit_done")) {
			Survey survey = (Survey) session
					.getAttribute(GlobaleNames.CURRENT_SURVEY);

			Integer surveyId = survey.getSurveyId();

			engageService.parseAndSave(allBagMap, surveyId);

		}

		session.removeAttribute(GlobaleNames.CURRENT_SURVEY);
		session.removeAttribute(GlobaleNames.ALL_BAG_MAP);
		session.removeAttribute(GlobaleNames.LAST_INDEX);
		session.removeAttribute(GlobaleNames.BAGS);

		return "redirect:/index.jsp";
	}

	@RequestMapping("/guest/engage/entry/{surveyId}")
	public String entry(@PathVariable("surveyId") Integer surveyId,
			HttpSession session, HttpServletRequest request) {
		Survey survey = engageService.getEntityById(surveyId);
		session.setAttribute(GlobaleNames.CURRENT_SURVEY, survey);
		Set<Bag> bags = survey.getBags();
		List<Bag> bagList = new ArrayList<Bag>(bags);
		session.setAttribute(GlobaleNames.BAGS, bagList);
		int lastIndex = bagList.size() - 1;
		session.setAttribute(GlobaleNames.LAST_INDEX, lastIndex);
		Map<Integer, Map<String, String[]>> allBagMap = new HashMap<Integer, Map<String, String[]>>();
		session.setAttribute(GlobaleNames.ALL_BAG_MAP, allBagMap);
		Bag bag = bagList.get(0);
		request.setAttribute(GlobaleNames.CURRENT_BAG, bag);
		request.setAttribute(GlobaleNames.CURRENT_INDEX, 0);
		return "guest/engage_engage";
	}

	@RequestMapping("/guest/engage/showAllAvailable")
	public String showAllAvailable(
			@RequestParam(value = "pageNoStr", required = false) String pageNoStr,
			Map<String, Object> map) {
		Page<Survey> page = surveyService.getAllAvailable(pageNoStr);
		map.put(GlobaleNames.PAGE, page);

		return "guest/engage_list";
	}
}
