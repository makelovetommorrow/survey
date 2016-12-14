package com.atguigu.survey.component.service.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseService;
import com.atguigu.survey.entities.manager.Res;

/**
 * @author shuai xu
 * 2016年10月25日 上午11:05:33
 */
public interface ResService extends BaseService<Res>{

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
	 * @param resId
	 * @return
	 */
	boolean updateStatus(Integer resId);

	/**
	 * @param servletPath
	 * @return
	 */
	Res getResByServletPath(String servletPath);

}
