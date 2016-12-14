package com.atguigu.survey.component.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.impl.BaseDaoImpl;
import com.atguigu.survey.component.dao.interfaces.AnswerDao;
import com.atguigu.survey.entities.guest.Answer;

/**
 * @author shuai xu 2016年10月23日 下午8:43:37
 */
@SuppressWarnings("all")
@Repository
public class AnswerDaoImpl extends BaseDaoImpl<Answer> implements AnswerDao {

	public void batchSaveAnswerList(List<Answer> answerList) {
		int size = answerList.size();

		String sql = "INSERT INTO `guest_answer`(" + "`CONTENT`," + "`UUID`,"
				+ "`SURVEY_ID`," + "`QUESTION_ID`) VALUES(?,?,?,?)";

		Object[][] params = new Object[size][4];

		for (int i = 0; i < size; i++) {

			Answer answer = answerList.get(i);

			params[i] = new Object[] { answer.getContent(), answer.getUuid(),
					answer.getSurveyId(), answer.getQuestionId() };

		}

		batchUpdate(sql, params);
	}

	
	public List<String> getSimpleContentList(Integer questionId) {
		String sql="select content from guest_answer where question_id=?";
		return getEntityListBySql(sql, questionId);
	}


	public int getQuestionEngagedCount(Integer questionId) {
		String sql="select count(*) from guest_answer where question_id=?";
		return getCountBySql(sql, questionId);
	}


	public int getOptionEngagedCount(Integer questionId, int i) {
		String indexParam="%,"+i+",%";
		String sql="select count(*) from guest_answer where question_id=? and concat(',',content,',') like ?";
		
		return getCountBySql(sql,questionId, indexParam);
	}


	public int getSurveyEngagedCount(Integer surveyId) {
		String sql="select count(distinct `uuid`) from guest_answer where survey_id=?";
		return getCountBySql(sql, surveyId);
	}


	public List<Answer> getListBySurveyId(Integer surveyId) {
		String hql="from Answer a where a.surveyId=?";
		return getEntityListByHql(hql, surveyId);
	}
 
}
