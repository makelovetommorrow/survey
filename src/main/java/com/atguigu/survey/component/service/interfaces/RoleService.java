package com.atguigu.survey.component.service.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseService;
import com.atguigu.survey.entities.manager.Role;

/**
 * @author shuai xu
 * 2016年10月25日 上午11:16:41
 */
public interface RoleService extends BaseService<Role>{

	/**
	 * @return
	 */
	List<Role> getRoleList();

	/**
	 * @param roleIdList
	 */
	void batchDeleteRole(List<Integer> roleIdList);

	/**
	 * @param roleId
	 * @return
	 */
	List<Integer> getAuthChecked(Integer roleId);

	/**
	 * @param roleId 
	 * @param checkAuthIdList
	 */
	void dispatcher(Integer roleId, List<Integer> checkAuthIdList);

}
