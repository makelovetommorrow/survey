package com.atguigu.survey.component.dao.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.entities.guest.Answer;

/**
 * @author shuai xu 2016年10月23日 下午8:42:46
 */
public interface AnswerDao extends BaseDao<Answer> {

	/**
	 * @param answerList
	 */
	void batchSaveAnswerList(List<Answer> answerList);

	/**
	 * @param questionId
	 * @return
	 */
	List<String> getSimpleContentList(Integer questionId);

	/**
	 * @param questionId
	 * @return
	 */
	int getQuestionEngagedCount(Integer questionId);

	/**
	 * @param questionId
	 * @param i
	 * @return
	 */
	int getOptionEngagedCount(Integer questionId, int i);

	/**
	 * @param surveyId
	 * @return
	 */
	int getSurveyEngagedCount(Integer surveyId);
	
	List<Answer> getListBySurveyId(Integer surveyId);
}
