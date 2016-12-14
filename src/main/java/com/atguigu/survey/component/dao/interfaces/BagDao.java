package com.atguigu.survey.component.dao.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.entities.guest.Bag;

/**
 * @author shuai xu
 * 2016年10月19日 上午11:56:04
 */
public interface BagDao extends BaseDao<Bag>{

	/**
	 * @param bag
	 */
	void updateBag(Bag bag);

	/**
	 * @return
	 */
	List<Bag> getBagListBySurveyId(Integer surveyId);

	/**
	 * @param bagIdList
	 * @param bagOrderList
	 */
	void batchUpdateBagOrder(List<Integer> bagIdList, List<Integer> bagOrderList);
}
