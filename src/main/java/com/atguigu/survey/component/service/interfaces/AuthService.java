package com.atguigu.survey.component.service.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseService;
import com.atguigu.survey.entities.manager.Auth;

/**
 * @author shuai xu
 * 2016年10月25日 上午11:15:08
 */
public interface AuthService extends BaseService<Auth>{

	/**
	 * @return
	 */
	List<Auth> getAuthList();

	/**
	 * @param authIdList
	 */
	void batchDeleteAuth(List<Integer> authIdList);

	/**
	 * @return
	 */
	List<Integer> getOldResByAuthId(Integer authId);

	/**
	 * @param authId
	 * @param resList
	 */
	void dispatcher(Integer authId, List<Integer> resList);

}
