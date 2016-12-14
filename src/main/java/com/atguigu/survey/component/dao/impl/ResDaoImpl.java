package com.atguigu.survey.component.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.impl.BaseDaoImpl;
import com.atguigu.survey.component.dao.interfaces.ResDao;
import com.atguigu.survey.entities.manager.Res;

/**
 * @author shuai xu 2016年10月25日 上午11:02:20
 */
@Repository
public class ResDaoImpl extends BaseDaoImpl<Res> implements ResDao {

	public boolean checkServletPathExists(String servletPath) {
		String hql = "select count(*) from Res r where r.servletPath=?";
		return getCountByHql(hql, servletPath) > 0;
	}

	public Integer getMaxResPos() {
		String hql = "select max(r.resPos) from Res r";
		return (Integer) getQuery(hql).uniqueResult();
	}

	public Integer getMaxResCode(Integer maxPos) {
		String hql="select max(r.resCode) from Res r where r.resPos=?";
		return (Integer) getQuery(hql, maxPos).uniqueResult();
	}

	public List<Res> getResList() {
		return getEntityListByHql("From Res");
	}

	public void batchDeleteRes(List<Integer> resIdList) {
		String sql="delete from manager_res where res_id=?";
		Object[][] params=new Object[resIdList.size()][1];
		for (int i = 0; i < resIdList.size(); i++) {
			Integer resId = resIdList.get(i);
			params[i]=new Object[]{resId};
		}
		batchUpdate(sql, params);
	}

	public Res getResByServletPath(String servletPath) {
		String hql="From Res r where r.servletPath=?";
		return getEntityByHql(hql, servletPath);
	}

}
