package com.atguigu.survey.component.dao.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.entities.manager.Auth;

/**
 * @author shuai xu
 * 2016年10月25日 上午11:03:09
 */
public interface AuthDao extends BaseDao<Auth> {

	/**
	 * @return
	 */
	List<Auth> getAuthList();

	/**
	 * @param authIdList
	 */
	void batchDeleteAuth(List<Integer> authIdList);

	/**
	 * @param authId
	 * @return
	 */
	List<Integer> getOldResByAuthId(Integer authId);

	/**
	 * @param authId
	 */
	void removeOldRelation(Integer authId);

	/**
	 * @param resList
	 */
	void saveNewRelation(Integer authId,List<Integer> resList);

}
