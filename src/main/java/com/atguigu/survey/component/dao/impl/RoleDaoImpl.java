package com.atguigu.survey.component.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.impl.BaseDaoImpl;
import com.atguigu.survey.component.dao.interfaces.RoleDao;
import com.atguigu.survey.entities.manager.Role;

/**
 * @author shuai xu 2016年10月25日 上午11:04:35
 */
@SuppressWarnings("all")
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	public List<Role> getRoleList() {

		return getEntityListByHql("from Role");
	}

	public void batchDeleteRole(List<Integer> roleIdList) {
		String sql = "delete from manager_role where role_id=?";
		Object[][] params = new Object[roleIdList.size()][1];
		for (int i = 0; i < roleIdList.size(); i++) {
			Integer roleId = roleIdList.get(i);
			params[i] = new Object[] { roleId };
		}
		batchUpdate(sql, params);
	}

	public List<Integer> getAuthChecked(Integer roleId) {
		String sql = "SELECT i.`AUTH_ID` FROM inner_role_auth i WHERE ROLE_ID=?";
		return getEntityListBySql(sql, roleId);
	}

	public void removeOldRelation(Integer roleId) {
		String sql = "delete from inner_role_auth where role_id=?";
		updateBySql(sql, roleId);
	}

	public void saveNewRelation(Integer roleId, List<Integer> checkAuthIdList) {
		String sql = "insert into inner_role_auth(role_id,auth_id) values(?,?)";
		Object[][] params = new Object[checkAuthIdList.size()][2];
		for (int i = 0; i < checkAuthIdList.size(); i++) {
			Integer checkAuthId = checkAuthIdList.get(i);
			params[i] = new Object[] { roleId, checkAuthId };
		}
		batchUpdate(sql, params);
	}

	public Role getRoleByName(String roleName) {
		String hql="from Role r where r.roleName=?";
		return getEntityByHql(hql, roleName);
	}

}
