package com.atguigu.survey.component.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.impl.BaseDaoImpl;
import com.atguigu.survey.component.dao.interfaces.SurveyDao;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.entities.guest.User;

/**
 * @author shuai xu 2016年10月18日 上午11:02:24
 */
@Repository
public class SurveyDaoImpl extends BaseDaoImpl<Survey> implements SurveyDao {

	public int getMyUnCompletedCount(User user) {
		String hql="select count(*) from Survey s where s.user=? and s.completed=false";
		return getCountByHql(hql, user);
	}

	public List<Survey> getMyUnCompletedList(User user, int pageNo, int pageSize) {
		String hql="from Survey s where s.user=? and s.completed=false order by s.surveyId desc";
		return getLimitedListByHql(pageNo, pageSize, hql, user);
	}

	public void updateSurvey(Survey survey) {
		String hql="update Survey s set s.surveyName=?,s.logoPath=? where s.surveyId=?";
		updateByHql(hql, survey.getSurveyName(),survey.getLogoPath(),survey.getSurveyId());
	}

	public void moveToSurvey(Integer surveyId, Integer bagId) {
		String sql="update survey_bag set survey_id=? where bag_id=?";
		updateBySql(sql, surveyId,bagId);
	}

	public int getAllAvailableCount() {
		String hql="select count(*) from Survey s where s.completed=true";
		return getCountByHql(hql);
	}

	public List<Survey> getAllAvailableList(int pageNo, int pageSize) {
		String hql="from Survey s where s.completed=true";
		return getLimitedListByHql(pageNo, pageSize, hql);
	}
	
}
