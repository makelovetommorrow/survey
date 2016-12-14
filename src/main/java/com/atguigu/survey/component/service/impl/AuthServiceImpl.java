package com.atguigu.survey.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.AdminDao;
import com.atguigu.survey.component.dao.interfaces.AuthDao;
import com.atguigu.survey.component.dao.interfaces.ResDao;
import com.atguigu.survey.component.service.interfaces.AuthService;
import com.atguigu.survey.entities.manager.Auth;

/**
 * @author shuai xu 2016年10月25日 上午11:15:41
 */
@Service
public class AuthServiceImpl extends BaseServiceImpl<Auth> implements
		AuthService {
	@Autowired
	private AuthDao authDao;
	@Autowired
	private ResDao resDao;
	@Autowired
	private AdminDao adminDao;

	public List<Auth> getAuthList() {

		return authDao.getAuthList();
	}

	public void batchDeleteAuth(List<Integer> authIdList) {
		authDao.batchDeleteAuth(authIdList);
	}

	public List<Integer> getOldResByAuthId(Integer authId) {

		return authDao.getOldResByAuthId(authId);
	}

	public void dispatcher(Integer authId, List<Integer> resList) {
		authDao.removeOldRelation(authId);
		if (resList != null) {
			authDao.saveNewRelation(authId, resList);
		}
		Integer maxResPos = resDao.getMaxResPos();
		adminDao.reCalculateCodeArr(maxResPos);
	}
}
