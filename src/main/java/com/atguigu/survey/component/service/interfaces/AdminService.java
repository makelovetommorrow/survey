package com.atguigu.survey.component.service.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseService;
import com.atguigu.survey.entities.manager.Admin;

public interface AdminService extends BaseService<Admin>{

	Admin login(Admin admin);

	/**
	 * @param admin
	 */
	void regist(Admin admin);

	/**
	 * @return
	 */
	List<Admin> getAdminList();

	/**
	 * @return
	 */
	List<Integer> getRoleIdByAdminId(Integer adminId);

	/**
	 * @param adminId
	 * @param newRoleIdList
	 */
	void dispatcher(Integer adminId, List<Integer> newRoleIdList);

}
