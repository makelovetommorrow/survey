package com.atguigu.survey.component.service.interfaces;

import com.atguigu.survey.base.interfaces.BaseService;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.model.Page;

/**
 * @author shuai xu 2016年10月18日 上午10:56:33
 */
public interface SurveyService extends BaseService<Survey> {

	/**
	 * 
	 */
	Page<Survey> getMyUnCompleted(User user,String pageNoStr);

	/**
	 * @param survey
	 */
	void updateSurvey(Survey survey);

	/**
	 * @param surveyId
	 */
	void removeSurveyDeeply(Integer surveyId);

	/**
	 * @param surveyId
	 */
	void complete(Integer surveyId);

	/**
	 * @param surveyId
	 * @param bagId
	 */
	void moveToSurvey(Integer surveyId, Integer bagId);

	/**
	 * @param pageNoStr
	 * @return
	 */
	Page<Survey> getAllAvailable(String pageNoStr);

}
