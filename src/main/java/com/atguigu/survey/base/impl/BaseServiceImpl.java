package com.atguigu.survey.base.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.base.interfaces.BaseService;

/**
 * @author shuai xu
 * 2016年10月16日 下午5:37:41
 */
public class BaseServiceImpl<T> implements BaseService<T> {
	@Autowired
	private BaseDao<T> baseDao;
	public void saveEntity(T t) {
		baseDao.saveEntity(t);
	}

	public void removeEntity(Integer entityId) {
		baseDao.removeEntity(entityId);
	}

	public void removeEntity(T t) {
		baseDao.removeEntity(t);
	}

	public void updateEntity(T t) {
		baseDao.updateEntity(t);
	}

	public T getEntityById(Integer entityId) {
		return baseDao.getEntityById(entityId);
	}

}
