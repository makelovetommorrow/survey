package com.atguigu.survey.component.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.AdminDao;
import com.atguigu.survey.component.dao.interfaces.ResDao;
import com.atguigu.survey.component.service.interfaces.AdminService;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.exceptions.AdminLoginFailedException;
import com.atguigu.survey.exceptions.AdminNameExistsException;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobalMessage;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements
		AdminService {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ResDao resDao;

	public Admin login(Admin admin) {

		String adminName = admin.getAdminName();
		String adminPwd = admin.getAdminPwd();

		adminPwd = DataprocessUtils.md5(adminPwd);

		Admin adminLogin = adminDao.getAdminForLogin(adminName, adminPwd);

		if (adminLogin == null) {
			throw new AdminLoginFailedException(
					GlobalMessage.ADMIN_LOGIN_FAILED);
		}

		return adminLogin;
	}

	public void regist(Admin admin) {
		boolean exists = adminDao.checkAdminName(admin.getAdminName());
		if (exists) {
			throw new AdminNameExistsException(GlobalMessage.ADMIN_NAME_EXISTS);
		}
		String adminPwd = admin.getAdminPwd();
		String md5 = DataprocessUtils.md5(adminPwd);
		admin.setAdminPwd(md5);
		adminDao.saveEntity(admin);
	}

	public List<Admin> getAdminList() {

		return adminDao.getAdminList();
	}

	public List<Integer> getRoleIdByAdminId(Integer adminId) {

		return adminDao.getRoleIdByAdminId(adminId);
	}

	public void dispatcher(Integer adminId, List<Integer> newRoleIdList) {
		adminDao.removeOldRelation(adminId);
		if (newRoleIdList != null) {
			adminDao.saveNewRelation(adminId, newRoleIdList);
		}
		Integer maxResPos = resDao.getMaxResPos();
		Admin admin = adminDao.getEntityById(adminId);
		Set<Role> roleSet = admin.getRoleSet();
		String codeArrStr = DataprocessUtils.calcalateCode(maxResPos, roleSet);
		admin.setCodeArrStr(codeArrStr);
	}

}
