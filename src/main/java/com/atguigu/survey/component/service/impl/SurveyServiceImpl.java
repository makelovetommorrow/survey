package com.atguigu.survey.component.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.SurveyDao;
import com.atguigu.survey.component.service.interfaces.SurveyService;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.exceptions.BagEmptyException;
import com.atguigu.survey.exceptions.SurveyEmptyException;
import com.atguigu.survey.model.Page;
import com.atguigu.survey.utils.GlobalMessage;

/**
 * @author shuai xu 2016年10月18日 上午10:58:02
 */
@Service
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements
		SurveyService {
	@Autowired
	private SurveyDao surveyDao;

	/**
	 * 设置带分页的类信息
	 */
	public Page<Survey> getMyUnCompleted(User user, String pageNoStr) {
		int totalRecordNo = surveyDao.getMyUnCompletedCount(user);
		Page<Survey> page = new Page<Survey>(pageNoStr, totalRecordNo);
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		List<Survey> list = surveyDao.getMyUnCompletedList(user, pageNo,
				pageSize);
		page.setList(list);
		return page;
	}

	public void updateSurvey(Survey survey) {
		surveyDao.updateSurvey(survey);
	}

	public void removeSurveyDeeply(Integer surveyId) {
		Survey survey = surveyDao.getEntityById(surveyId);
		surveyDao.removeEntity(survey);
	}

	public void complete(Integer surveyId) {
		Survey survey = surveyDao.getEntityById(surveyId);
		Set<Bag> bags = survey.getBags();
		if (bags == null || bags.size() == 0) {
			throw new SurveyEmptyException(GlobalMessage.SURVEY_EMPTY);
		}
		for (Bag bag : bags) {
			Set<Question> questions = bag.getQuestions();
			if (questions == null || questions.size() == 0) {
				throw new BagEmptyException(GlobalMessage.BAG_EMPTY);
			}
		}
		survey.setCompleted(true);
	}

	public void moveToSurvey(Integer surveyId, Integer bagId) {
		surveyDao.moveToSurvey(surveyId, bagId);
	}

	public Page<Survey> getAllAvailable(String pageNoStr) {
		int totalRecordNo = surveyDao.getAllAvailableCount();
		Page<Survey> page = new Page<Survey>(pageNoStr, totalRecordNo);
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		List<Survey> list = surveyDao.getAllAvailableList(pageNo, pageSize);
		page.setList(list);
		return page;
	}

}
