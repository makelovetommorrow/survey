package com.atguigu.survey.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.ResDao;
import com.atguigu.survey.component.service.interfaces.ResService;
import com.atguigu.survey.entities.manager.Res;

/**
 * @author shuai xu 2016年10月25日 上午11:05:53
 */
@Service
public class ResServiceImpl extends BaseServiceImpl<Res> implements ResService {
	@Autowired
	private ResDao resDao;

	public boolean checkServletPathExists(String servletPath) {

		return resDao.checkServletPathExists(servletPath);
	}

	public Integer getMaxResPos() {
		
		return resDao.getMaxResPos();
	}

	public Integer getMaxResCode(Integer maxPos) {
		
		return resDao.getMaxResCode(maxPos);
	}

	public List<Res> getResList() {
		return resDao.getResList();
	}

	public void batchDeleteRes(List<Integer> resIdList) {
		resDao.batchDeleteRes(resIdList);
	}

	public boolean updateStatus(Integer resId) {
		Res res = resDao.getEntityById(resId);
		res.setPublicStatus(!res.isPublicStatus());
		return res.isPublicStatus();
	}

	public Res getResByServletPath(String servletPath) {
		
		return resDao.getResByServletPath(servletPath);
	}
}
