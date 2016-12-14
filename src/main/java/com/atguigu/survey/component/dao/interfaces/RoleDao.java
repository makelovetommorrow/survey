package com.atguigu.survey.component.dao.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.entities.manager.Role;

/**
 * @author shuai xu
 * 2016年10月25日 上午11:04:10
 */
public interface RoleDao extends BaseDao<Role>{

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
	 */
	void removeOldRelation(Integer roleId);

	/**
	 * @param checkAuthIdList
	 */
	void saveNewRelation(Integer roleId,List<Integer> checkAuthIdList);

	/**
	 * @param roleName
	 * @return
	 */
	Role getRoleByName(String roleName);

}
