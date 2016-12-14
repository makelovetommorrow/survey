package com.atguigu.survey.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.LogDao;
import com.atguigu.survey.component.service.interfaces.LogService;
import com.atguigu.survey.entities.manager.Log;
import com.atguigu.survey.model.Page;

/**
 * @author shuai xu
 * 2016年10月31日 上午10:17:08
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService{
	@Autowired
	private LogDao logDao;
	//persist:坚持，持续
	public void persistLog(Log log) {
		logDao.saveLog(log);
	}
	public void createTable(String tableName) {
		logDao.createTable(tableName);
	}
	public Page<Log> getPage(String pageNoStr) {
		int totalRecordNo = logDao.getLogCount();
		Page<Log> page=new Page<Log>(pageNoStr,totalRecordNo);
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		List<Log> list = logDao.getLogLimitedList(pageNo, pageSize);
		page.setList(list);
		return page;
	}
	
}
