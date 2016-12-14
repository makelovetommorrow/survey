package com.atguigu.survey.component.dao.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.entities.manager.Log;

/**
 * @author shuai xu 2016年10月31日 上午10:15:05
 */
public interface LogDao extends BaseDao<Log> {

	/**
	 * @param tableName
	 */
	void createTable(String tableName);

	void saveLog(Log log);

	List<String> getLogTableNames();

	int getLogCount();

	List<Log> getLogLimitedList(int pageNo, int pageSize);
}
