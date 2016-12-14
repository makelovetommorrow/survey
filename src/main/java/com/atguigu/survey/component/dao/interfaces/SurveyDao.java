package com.atguigu.survey.component.dao.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.entities.guest.User;

/**
 * @author shuai xu
 * 2016年10月18日 上午11:00:44
 */
public interface SurveyDao extends BaseDao<Survey>{
	
	int getMyUnCompletedCount(User user);
	List<Survey> getMyUnCompletedList(User user,int pageNo,int pageSize);
	/**
	 * @param survey
	 */
	void updateSurvey(Survey survey);
	/**
	 * @param surveyId
	 * @param bagId
	 */
	void moveToSurvey(Integer surveyId, Integer bagId);
	/**
	 * @return
	 */
	int getAllAvailableCount();
	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Survey> getAllAvailableList(int pageNo, int pageSize);
}
