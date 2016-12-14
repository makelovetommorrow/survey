package com.atguigu.survey.component.dao.interfaces;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.entities.guest.User;

/**
 * @author shuai xu
 * 2016年10月16日 下午7:11:29
 */
public interface UserDao extends BaseDao<User>{

	/**
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	User getUserForLogin(String userName, String userPwd);

	/**
	 * @param userName
	 * @return
	 */
	boolean checkUserName(String userName);

}
