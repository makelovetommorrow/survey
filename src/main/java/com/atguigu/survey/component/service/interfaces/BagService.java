package com.atguigu.survey.component.service.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseService;
import com.atguigu.survey.entities.guest.Bag;

/**
 * @author shuai xu
 * 2016年10月19日 上午11:57:29
 */
public interface BagService extends BaseService<Bag>{

	/**
	 * @param bag
	 */
	void saveBag(Bag bag);

	/**
	 * @param bagId
	 */
	void removeBag(Integer bagId);

	/**
	 * @param bag
	 */
	void updateBag(Bag bag);

	/**
	 * @param bagId
	 */
	void removeBagDeeply(Integer bagId);

	/**
	 * @param surveyId
	 * @return
	 */
	List<Bag> getBagListBySurveyId(Integer surveyId);

	/**
	 * @param bagIdList
	 * @param bagOrderList
	 */
	void batchUpdateBagOrder(List<Integer> bagIdList, List<Integer> bagOrderList);

	/**
	 * @param surveyId
	 * @param bagId
	 */
	void copyToSurvey(Integer surveyId, Integer bagId);


}
