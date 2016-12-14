package com.atguigu.survey.component.dao.impl;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.impl.BaseDaoImpl;
import com.atguigu.survey.component.dao.interfaces.UserDao;
import com.atguigu.survey.entities.guest.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public User getUserForLogin(String userName, String userPwd) {
		String hql="FROM User u where u.userName=? and u.userPwd=?";
		
		return getEntityByHql(hql, userName,userPwd);
	}

	public boolean checkUserName(String userName) {
		String hql="From User u where u.userName=?";
		User user = getEntityByHql(hql, userName);
		return user!=null;
	}

}
