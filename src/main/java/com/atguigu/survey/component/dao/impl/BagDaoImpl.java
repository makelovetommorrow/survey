package com.atguigu.survey.component.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.impl.BaseDaoImpl;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.component.dao.interfaces.BagDao;

/**
 * @author shuai xu 2016年10月19日 上午11:56:38
 */
@Repository
public class BagDaoImpl extends BaseDaoImpl<Bag> implements BagDao {

	public void updateBag(Bag bag) {
		String hql = "update Bag b set b.bagName=? where b.bagId=?";
		updateByHql(hql, bag.getBagName(), bag.getBagId());
	}

	public List<Bag> getBagListBySurveyId(Integer surveyId) {
		String hql="FROM Bag b where b.survey.surveyId=?";
		return getEntityListByHql(hql, surveyId);
	}

	public void batchUpdateBagOrder(List<Integer> bagIdList,
			List<Integer> bagOrderList) {
		int size = bagIdList.size();
		String sql="update survey_bag set bag_order=? where bag_id=?";
		Object[][] params=new Object[size][2];
		for(int i=0;i<size;i++){
			Integer bagId = bagIdList.get(i);
			Integer bagOrder = bagOrderList.get(i);
			params[i]=new Object[]{bagOrder,bagId};
		}
		batchUpdate(sql, params);
	}

}
