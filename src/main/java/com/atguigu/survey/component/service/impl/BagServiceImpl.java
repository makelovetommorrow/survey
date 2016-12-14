package com.atguigu.survey.component.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.BagDao;
import com.atguigu.survey.component.dao.interfaces.QuestionDao;
import com.atguigu.survey.component.dao.interfaces.SurveyDao;
import com.atguigu.survey.component.service.interfaces.BagService;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.utils.DataprocessUtils;

/**
 * @author shuai xu 
 * 2016年10月19日 上午11:57:49
 */
@Service
public class BagServiceImpl extends BaseServiceImpl<Bag> implements BagService {
	@Autowired
	private BagDao bagDao;
	@Autowired
	private QuestionDao questionDao;
	public void saveBag(Bag bag) {
		bagDao.saveEntity(bag);
		bag.setBagOrder(bag.getBagId());
	}

	public void removeBag(Integer bagId) {
		bagDao.removeEntity(bagId);
	}

	public void updateBag(Bag bag) {
		bagDao.updateBag(bag);
	}

	public void removeBagDeeply(Integer bagId) {
		Bag bag = bagDao.getEntityById(bagId);
		bagDao.removeEntity(bag);
	}

	public List<Bag> getBagListBySurveyId(Integer surveyId) {
		
		return bagDao.getBagListBySurveyId(surveyId);
	}

	public void batchUpdateBagOrder(List<Integer> bagIdList,
			List<Integer> bagOrderList) {
		bagDao.batchUpdateBagOrder(bagIdList,bagOrderList);
	}

	public void copyToSurvey(Integer surveyId, Integer bagId) {
		Bag bagSource = bagDao.getEntityById(bagId);
		Bag bagTarget = (Bag) DataprocessUtils.deeplyCopy(bagSource);
		bagTarget.setSurvey(new Survey(surveyId));
		bagDao.saveEntity(bagTarget);
		Set<Question> questions=bagTarget.getQuestions();
		questionDao.batchSaveQuestion(questions);
	}

}
