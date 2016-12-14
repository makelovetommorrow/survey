package com.atguigu.survey.component.handler.guest;

import java.util.HashSet;
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

import com.atguigu.survey.component.service.interfaces.BagService;
import com.atguigu.survey.component.service.interfaces.SurveyService;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.exceptions.BagOrderDuplicateException;
import com.atguigu.survey.exceptions.RemoveBagFailedException;
import com.atguigu.survey.model.Page;
import com.atguigu.survey.utils.GlobalMessage;
import com.atguigu.survey.utils.GlobaleNames;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * @author shuai xu 2016年10月19日 下午6:09:59
 */
@Controller
public class BagHandler {
	@Autowired
	private BagService bagService;
	@Autowired
	private SurveyService surveyService;
	@RequestMapping("/guest/bag/copyToSurvey/{surveyId}/{bagId}")
	public String copyToSurvey(
			@PathVariable("surveyId")Integer surveyId,
			@PathVariable("bagId")Integer bagId
			){
		bagService.copyToSurvey(surveyId,bagId);
		return "redirect:/guest/survey/toDesignUI/" + surveyId;
	}
	@RequestMapping("/guest/bag/moveToSurvey/{surveyId}/{bagId}")
	public String moveToSurvey(
			@PathVariable("surveyId")Integer surveyId,
			@PathVariable("bagId")Integer bagId
			){
		surveyService.moveToSurvey(surveyId,bagId);
		return "redirect:/guest/survey/toDesignUI/" + surveyId;
	}
	//去移动、复制调查页面
	@RequestMapping("/guest/bag/showTargetSurveys/{bagId}/{surveyId}")
	public String showTargetSurveys(
			@PathVariable("bagId")Integer bagId,
			@PathVariable("surveyId")Integer surveyId,
			Map<String,Object> map,
			HttpSession session,
			@RequestParam(value="pageNoStr",required=false)String pageNoStr
			){
			User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
			Page<Survey> page = surveyService.getMyUnCompleted(user, pageNoStr);
			map.put(GlobaleNames.PAGE, page);
		return "guest/survey_moveOrCopy";
	}
	@RequestMapping("/guest/bag/doAdjust")
	public String doAdjust(
			@RequestParam("bagIdList")List<Integer> bagIdList,
			@RequestParam("bagOrderList")List<Integer> bagOrderList,
			@RequestParam("surveyId")Integer surveyId,
			HttpServletRequest request
			){
		for (int i = 0; i < bagIdList.size(); i++) {
			Integer bagId = bagIdList.get(i);
			Integer bagOrder = bagOrderList.get(i);
			System.out.println(bagId+":"+bagOrder);
		}
		Set<Integer> bagOrderSet=new HashSet<Integer>(bagOrderList);
		if(bagOrderList.size()!=bagOrderSet.size()){
			List<Bag> bagList = bagService.getBagListBySurveyId(surveyId);
			request.setAttribute("surveyId", surveyId);
			request.setAttribute("bagList", bagList);
			throw new BagOrderDuplicateException(GlobalMessage.BAD_ORDER_DUPLICATE);
		}
		bagService.batchUpdateBagOrder(bagIdList,bagOrderList);
		return "redirect:/guest/survey/toDesignUI/" + surveyId;
	}
	@RequestMapping("/guest/bag/toAdjustUI/{surveyId}")
	public String toAdjustUI(@PathVariable("surveyId")Integer surveyId
			,Map<String,Object> map){
		List<Bag> bagList=bagService.getBagListBySurveyId(surveyId);
		map.put("bagList", bagList);
		return "guest/bag_adjustUI";
	}
	@RequestMapping("/guest/bag/removeBagDeeply/{bagId}/{surveyId}")
	public String removeBagDeeply(
			@PathVariable("bagId")Integer bagId,
			@PathVariable("surveyId")Integer surveyId
			){
		bagService.removeBagDeeply(bagId);
		return "redirect:/guest/survey/toDesignUI/" + surveyId;
	}
	@RequestMapping("/guest/bag/updateBag")
	public String updateBag(Bag bag) {
		bagService.updateBag(bag);
		return "redirect:/guest/survey/toDesignUI/"
				+ bag.getSurvey().getSurveyId();
	}

	@RequestMapping("/guest/bag/toEditUI/{bagId}")
	public String toEditUI(@PathVariable("bagId") Integer bagId,
			Map<String, Object> map) {
		Bag bag = bagService.getEntityById(bagId);
		map.put("bag", bag);
		return "guest/bag_editUI";
	}

	@RequestMapping("/guest/bag/removeBag/{bagId}/{surveyId}")
	public String removeBag(@PathVariable("bagId") Integer bagId,
			@PathVariable("surveyId") Integer surveyId) throws Exception {
		try {
			bagService.removeBag(bagId);
		} catch (Exception e) {
			Throwable cause = e.getCause();
			if(cause!=null && cause instanceof MySQLIntegrityConstraintViolationException){
				throw new RemoveBagFailedException(GlobalMessage.REMOVE_BAG_FAILED);
			}
			throw e;
		}
		
		return "redirect:/guest/survey/toDesignUI/" + surveyId;
	}

	@RequestMapping("/guest/bag/saveBag")
	public String saveBag(Bag bag) {
		bagService.saveBag(bag);
		Integer surveyId = bag.getSurvey().getSurveyId();
		return "redirect:/guest/survey/toDesignUI/" + surveyId;
	}

	@RequestMapping("/guest/bag/toAddUI/{surveyId}")
	public String toAddUI(@PathVariable("surveyId") Integer surveyId) {
		return "guest/bag_addUI";
	}
}
