package com.atguigu.survey.base.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.survey.base.interfaces.BaseDao;

/**
 * @author shuai xu 2016年10月16日 下午3:20:32
 */
@SuppressWarnings("all")
public class BaseDaoImpl<T> implements BaseDao<T> {
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> entityType;

	/**
	 * 
	 */
	public BaseDaoImpl() {
		//System.err.println(this);
		Class<?> superclass = this.getClass().getSuperclass();
		//System.err.println(superclass);
		Type type = this.getClass().getGenericSuperclass();
		//System.err.println(type);
		ParameterizedType type2 = (ParameterizedType) type;
		Type[] types = type2.getActualTypeArguments();
		this.entityType = (Class<T>) types[0];
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
		// return sessionFactory.openSession();
	}

	public Query getQuery(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				query.setParameter(i, param);
			}
		}
		return query;
	}

	public SQLQuery getSqlQuey(String sql, Object... params) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				query.setParameter(i, param);
			}
		}
		return query;
	}

	public void saveEntity(T t) {
		getSession().save(t);
	}

	public void removeEntity(Integer entityId) {
		String name = this.entityType.getSimpleName();
		ClassMetadata metadata = sessionFactory.getClassMetadata(entityType);
		String id = metadata.getIdentifierPropertyName();
		String hql = "delete from " + name + " alias where alias." + id + "=?";
		getQuery(hql, entityId).executeUpdate();

	}

	public void removeEntity(T t) {
		getSession().delete(t);
	}

	public void updateEntity(T t) {
		getSession().update(t);
	}

	public void updateByHql(String hql, Object... params) {
		getQuery(hql, params).executeUpdate();
	}

	public void updateBySql(String sql, Object... params) {
		getSqlQuey(sql, params).executeUpdate();
	}

	public void batchUpdate(final String sql, final Object[][] params) {
		getSession().doWork(new Work() {

			public void execute(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						Object[] param = params[i];
						for (int j = 0; j < param.length; j++) {
							Object value = param[j];
							ps.setObject(j + 1, value);
						}
						ps.addBatch();
					}
					ps.executeBatch();
				}
				if (ps != null) {
					ps.close();
				}
			}

		});
	}

	public T getEntityById(Integer entityId) {
		return (T) getSession().get(entityType, entityId);
	}

	public T getEntityByHql(String hql, Object... params) {
		return (T) getQuery(hql, params).uniqueResult();
	}

	public List<T> getEntityListByHql(String hql, Object... params) {
		return getQuery(hql, params).list();
	}

	public List getEntityListBySql(String sql, Object... params) {
		return getSqlQuey(sql, params).list();
	}

	public int getCountByHql(String hql, Object... params) {
		long count = (Long) getQuery(hql, params).uniqueResult();

		return (int) count;
	}

	public int getCountBySql(String sql, Object... params) {
		BigInteger uniqueResult = (BigInteger) getSqlQuey(sql, params)
				.uniqueResult();
		return uniqueResult.intValue();
	}

	public List getLimitedListBySql(int pageNo, int pageSize, String sql,
			Object... params) {
		int index=(pageNo-1)*pageSize;
		return getSqlQuey(sql, params).setFirstResult(index).setMaxResults(pageSize).list();
	}

	public List<T> getLimitedListByHql(int pageNo, int pageSize, String hql,
			Object... params) {
		int index=(pageNo-1)*pageSize;
		return getQuery(hql, params).setFirstResult(index).setMaxResults(pageSize).list();
	}


}
