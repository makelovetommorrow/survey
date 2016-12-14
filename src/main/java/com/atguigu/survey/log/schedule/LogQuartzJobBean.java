package com.atguigu.survey.log.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.atguigu.survey.component.service.interfaces.LogService;
import com.atguigu.survey.log.thread.DataSourceKeyBinder;
import com.atguigu.survey.utils.DataprocessUtils;

/**
 * @author shuai xu
 * 2016年10月31日 下午6:46:32
 */
public class LogQuartzJobBean extends QuartzJobBean{
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		DataSourceKeyBinder.bindKey(DataSourceKeyBinder.DATASOURCE_LOG_KEY);
		String tableName = DataprocessUtils.generateTableName(1);
		logService.createTable(tableName);
		
		DataSourceKeyBinder.bindKey(DataSourceKeyBinder.DATASOURCE_LOG_KEY);
		tableName = DataprocessUtils.generateTableName(2);
		logService.createTable(tableName);
		
		DataSourceKeyBinder.bindKey(DataSourceKeyBinder.DATASOURCE_LOG_KEY);
		tableName = DataprocessUtils.generateTableName(3);
		logService.createTable(tableName);
	}
	
}
