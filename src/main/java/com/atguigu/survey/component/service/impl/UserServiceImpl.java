package com.atguigu.survey.component.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.ResDao;
import com.atguigu.survey.component.dao.interfaces.RoleDao;
import com.atguigu.survey.component.dao.interfaces.UserDao;
import com.atguigu.survey.component.service.interfaces.UserService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.exceptions.UserLoginFailedException;
import com.atguigu.survey.exceptions.UserNameExistsException;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobalMessage;

/**
 * @author shuai xu 2016年10月16日 下午7:14:32
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ResDao resDao;
	public User login(User user) {
		String userName = user.getUserName();
		String userPwd = user.getUserPwd();
		userPwd = DataprocessUtils.md5(userPwd);
		User loginUser = userDao.getUserForLogin(userName, userPwd);
		if (loginUser == null) {
			throw new UserLoginFailedException(GlobalMessage.USER_LOGIN_FAILED);
		}
		return loginUser;
	}

	public void regist(User user) {
		boolean exists = userDao.checkUserName(user.getUserName());
		if (exists) {
			throw new UserNameExistsException(GlobalMessage.USER_NAME_EXISTS);
		}
		String userPwd = user.getUserPwd();
		String md5 = DataprocessUtils.md5(userPwd);
		user.setUserPwd(md5);
		boolean company = user.isCompany();
		String roleName=null;
		if(company){
			roleName="企业用户";
		}else{
			roleName="个人用户";
		}
		Role role=roleDao.getRoleByName(roleName);
		Set<Role> roleSet=new HashSet<Role>();
		roleSet.add(role);
		user.setRoleSet(roleSet);
		Integer maxPos = resDao.getMaxResPos();
		String codeArrStr = DataprocessUtils.calcalateCode(maxPos, roleSet);
		user.setCodeArrStr(codeArrStr);
		userDao.saveEntity(user);
	}

}
