package com.atguigu.survey.component.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.impl.BaseDaoImpl;
import com.atguigu.survey.component.dao.interfaces.AuthDao;
import com.atguigu.survey.entities.manager.Auth;

/**
 * @author shuai xu
 * 2016年10月25日 上午11:03:31
 */
@Repository
public class AuthDaoImpl extends BaseDaoImpl<Auth> implements AuthDao{

	public List<Auth> getAuthList() {
		String hql="from Auth";
		return getEntityListByHql(hql);
	}

	public void batchDeleteAuth(List<Integer> authIdList) {
		String sql="delete from manager_auth where auth_id=?";
		Object[][] params=new Object[authIdList.size()][1];
		for(int i=0;i<authIdList.size();i++){
			Integer authId=authIdList.get(i);
			params[i]=new Object[]{authId};
		}
		batchUpdate(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getOldResByAuthId(Integer authId) {
		String sql="select res_id from inner_auth_res where auth_id=?";
		return getEntityListBySql(sql, authId);
	}

	public void removeOldRelation(Integer authId) {
		String sql="delete from inner_auth_res where auth_id=?";
		updateBySql(sql, authId);
	}

	public void saveNewRelation(Integer authId,List<Integer> resList) {
		String sql="insert into inner_auth_res(auth_id,res_id) values(?,?)";
		Object[][] params=new Object[resList.size()][2];
		for(int i=0;i<resList.size();i++){
			Integer resId = resList.get(i);
			params[i]=new Object[]{authId,resId};
		}
		batchUpdate(sql, params);
	}

}
