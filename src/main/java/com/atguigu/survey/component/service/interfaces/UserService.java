package com.atguigu.survey.component.service.interfaces;

import com.atguigu.survey.base.interfaces.BaseService;
import com.atguigu.survey.entities.guest.User;

/**
 * @author shuai xu 2016年10月16日 下午7:27:15
 */
public interface UserService extends BaseService<User> {

	/**
	 * @param user
	 * @return
	 */
	User login(User user);

	/**
	 * @param user
	 */
	void regist(User user);

}
