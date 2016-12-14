package com.atguigu.survey.component.service.interfaces;

import com.atguigu.survey.base.interfaces.BaseService;
import com.atguigu.survey.entities.manager.Log;
import com.atguigu.survey.model.Page;

/**
 * @author shuai xu
 * 2016年10月31日 上午10:16:34
 */
public interface LogService extends BaseService<Log> {

	/**
	 * @param log
	 */
	void persistLog(Log log);

	/**
	 * @param tableName
	 */
	void createTable(String tableName);
	Page<Log> getPage(String pageNoStr);
}
