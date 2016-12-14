package com.atguigu.survey.component.handler.guest;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.interfaces.QuestionService;
import com.atguigu.survey.entities.guest.Question;


/**
 * @author shuai xu
 * 2016年10月19日 下午7:39:43
 */
@Controller
public class QuestionHandler {
	@Autowired
	private QuestionService questionService;
	@RequestMapping("/quest/question/updateQuestion")
	public String updateQuestion(
			Question question,
			@RequestParam("surveyId")Integer surveyId
			){
		questionService.updateEntity(question);
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	@RequestMapping("/guest/question/toEditUI/{questionId}/{surveyId}")
	public String toEditUI(
			@PathVariable("questionId")Integer questionId,
			@PathVariable("surveyId")Integer surveyId,
			Map<String,Object> map){
		Question question = questionService.getEntityById(questionId);
		map.put("question", question);
		Map<Integer,String> radioMap=new LinkedHashMap<Integer, String>();
		radioMap.put(0, "单选题");
		radioMap.put(1, "多选题");
		radioMap.put(2, "简答题");
		map.put("radioMap", radioMap);
		return "guest/question_editUI";
		
	}
	@RequestMapping("/guest/survey/removeQuestion/{questionId}/{surveyId}")
	public String removeQuestion(
			@PathVariable("questionId")Integer questionId,
			@PathVariable("surveyId")Integer surveyId){
		questionService.removeEntity(questionId);
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	@RequestMapping("/guest/question/saveQuestion")
	public String saveQuestion(
			Question question,
			@RequestParam("surveyId")Integer surveyId
			){
		questionService.saveEntity(question);
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	@RequestMapping("/guest/question/toAddUI/{bagId}/{surveyId}")
	public String toAddUI(@PathVariable("bagId")Integer bagId,
			@PathVariable("surveyId")Integer surveyId){
		return "guest/question_addUI";
	}
}
