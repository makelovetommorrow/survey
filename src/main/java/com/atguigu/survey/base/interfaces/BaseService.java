package com.atguigu.survey.base.interfaces;

/**
 * @author shuai xu
 * 2016年10月16日 下午5:35:38
 */
public interface BaseService<T> {
	void saveEntity(T t);
	void removeEntity(Integer entityId);
	void removeEntity(T t);
	void updateEntity(T t);
	T getEntityById(Integer entityId);
}
