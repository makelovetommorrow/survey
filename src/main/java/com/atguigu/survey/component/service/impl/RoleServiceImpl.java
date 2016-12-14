package com.atguigu.survey.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.AdminDao;
import com.atguigu.survey.component.dao.interfaces.ResDao;
import com.atguigu.survey.component.dao.interfaces.RoleDao;
import com.atguigu.survey.component.service.interfaces.RoleService;
import com.atguigu.survey.entities.manager.Role;

/**
 * @author shuai xu 2016年10月25日 上午11:17:13
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ResDao resDao;
	@Autowired
	private AdminDao adminDao;

	public List<Role> getRoleList() {
		return roleDao.getRoleList();
	}

	public void batchDeleteRole(List<Integer> roleIdList) {
		roleDao.batchDeleteRole(roleIdList);
	}

	public List<Integer> getAuthChecked(Integer roleId) {
		return roleDao.getAuthChecked(roleId);
	}

	public void dispatcher(Integer roleId, List<Integer> checkAuthIdList) {
		roleDao.removeOldRelation(roleId);
		if (checkAuthIdList != null) {
			roleDao.saveNewRelation(roleId, checkAuthIdList);
		}
		Integer maxResPos = resDao.getMaxResPos();
		adminDao.reCalculateCodeArr(maxResPos);
	}
}
