package com.atguigu.survey.component.dao.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.entities.manager.Res;

/**
 * @author shuai xu
 * 2016年10月25日 上午11:01:40
 */
public interface ResDao extends BaseDao<Res>{

	/**
	 * @param servletPath
	 * @return
	 */
	boolean checkServletPathExists(String servletPath);

	/**
	 * @return
	 */
	Integer getMaxResPos();

	/**
	 * @param maxPos
	 * @return
	 */
	Integer getMaxResCode(Integer maxPos);

	/**
	 * @return
	 */
	List<Res> getResList();

	/**
	 * @param resIdList
	 */
	void batchDeleteRes(List<Integer> resIdList);

	/**
	 * @param servletPath
	 * @return
	 */
	Res getResByServletPath(String servletPath);

}
