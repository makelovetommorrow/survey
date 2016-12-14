package com.atguigu.survey.component.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.impl.BaseDaoImpl;
import com.atguigu.survey.component.dao.interfaces.AdminDao;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.DataprocessUtils;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {

	public Admin getAdminForLogin(String adminName, String adminPwd) {

		String hql = "From Admin a where a.adminName=? and a.adminPwd=?";

		return getEntityByHql(hql, adminName, adminPwd);
	}

	public boolean checkAdminName(String adminName) {
		String hql = "select count(*) from Admin a where a.adminName=?";
		return getCountByHql(hql, adminName) > 0;
	}

	public List<Admin> getAdminList() {
		String hql = "from Admin";
		return getEntityListByHql(hql);
	}


	@SuppressWarnings("unchecked")
	public List<Integer> getRoleIdByAdminId(Integer adminId) {
		String sql = "select role_id from inner_admin_role where admin_id=?";
		return getEntityListBySql(sql, adminId);
	}

	public void removeOldRelation(Integer adminId) {
		String sql = "delete from inner_admin_role where admin_id=?";
		updateBySql(sql, adminId);
	}

	public void saveNewRelation(Integer adminId, List<Integer> newRoleIdList) {
		String sql = "insert into inner_admin_role(admin_id,role_id) values(?,?)";
		Object[][] params = new Object[newRoleIdList.size()][2];
		for (int i = 0; i < newRoleIdList.size(); i++) {
			Integer roleId = newRoleIdList.get(i);
			params[i] = new Object[] { adminId, roleId };
		}
		batchUpdate(sql, params);
	}

	public void reCalculateCodeArr(Integer maxResPos) {
		List<Admin> adminList=getAdminList();
		for (Admin admin : adminList) {
			Set<Role> roleSet = admin.getRoleSet();
			String codeArrStr = DataprocessUtils.calcalateCode(maxResPos, roleSet);
			admin.setCodeArrStr(codeArrStr);
		}
		List<User> userList=getUserList();
		for (User user : userList) {
			Set<Role> roleSet = user.getRoleSet();
			String codeArrStr = DataprocessUtils.calcalateCode(maxResPos, roleSet);
			user.setCodeArrStr(codeArrStr);
		}
		
	}
	@SuppressWarnings("unchecked")
	public List<User> getUserList(){
		return getQuery("From User").list();
	}
}
