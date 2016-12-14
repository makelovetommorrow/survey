package com.atguigu.survey.base.interfaces;

import java.util.List;

/**
 * @author shuai xu
 * 2016年10月16日 下午3:09:21
 */
@SuppressWarnings("all")
public interface BaseDao<T> {
	void saveEntity(T t);
	
	void removeEntity(Integer entityId);
	
	void removeEntity(T t);
	
	void updateEntity(T t);
	
	void updateByHql(String hql,Object ... params);
	
	void updateBySql(String sql,Object ... params);
	
	void batchUpdate(String sql,Object[][] params);
	
	T getEntityById(Integer entityId);
	
	T getEntityByHql(String hql,Object ... params);
	
	List<T> getEntityListByHql(String hql,Object ... params);
	
	List getEntityListBySql(String sql,Object ... params);
	
	int getCountByHql(String hql,Object ... params);
	
	int getCountBySql(String sql,Object ... params);
	
	List<T> getLimitedListBySql(int pageNo,int pageSize,String sql,Object ... params);
	
	List<T> getLimitedListByHql(int pageNo,int pageSize,String hql,Object ... params);
	
}
