package com.atguigu.survey.log.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.atguigu.survey.component.service.interfaces.LogService;
import com.atguigu.survey.log.thread.DataSourceKeyBinder;
import com.atguigu.survey.utils.DataprocessUtils;

/**
 * @author shuai xu
 * 2016年10月31日 下午6:19:41
 */
public class LogListener implements ApplicationListener<ContextRefreshedEvent>{

	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext ioc = event.getApplicationContext();
		ApplicationContext parent = ioc.getParent();
		if(parent==null){
			LogService logService = ioc.getBean(LogService.class);
			DataSourceKeyBinder.bindKey(DataSourceKeyBinder.DATASOURCE_LOG_KEY);
			String tableName = DataprocessUtils.generateTableName(0);
			logService.createTable(tableName);
			
			DataSourceKeyBinder.bindKey(DataSourceKeyBinder.DATASOURCE_LOG_KEY);
			tableName = DataprocessUtils.generateTableName(1);
			logService.createTable(tableName);
			
			DataSourceKeyBinder.bindKey(DataSourceKeyBinder.DATASOURCE_LOG_KEY);
			tableName = DataprocessUtils.generateTableName(2);
			logService.createTable(tableName);
			
			DataSourceKeyBinder.bindKey(DataSourceKeyBinder.DATASOURCE_LOG_KEY);
			tableName = DataprocessUtils.generateTableName(3);
			logService.createTable(tableName);
			
		}
	}

}
