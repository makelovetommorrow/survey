package com.atguigu.survey.component.dao.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.entities.manager.Admin;

public interface AdminDao extends BaseDao<Admin>{

	Admin getAdminForLogin(String adminName, String adminPwd);

	/**
	 * @param adminName
	 * @return
	 */
	boolean checkAdminName(String adminName);

	/**
	 * @return
	 */
	List<Admin> getAdminList();

	/**
	 * @param adminId
	 * @return
	 */
	List<Integer> getRoleIdByAdminId(Integer adminId);

	/**
	 * @param adminId
	 */
	void removeOldRelation(Integer adminId);

	/**
	 * @param adminId
	 * @param newRoleIdList
	 */
	void saveNewRelation(Integer adminId, List<Integer> newRoleIdList);

	/**
	 * @param maxResPos
	 */
	void reCalculateCodeArr(Integer maxResPos);

}
