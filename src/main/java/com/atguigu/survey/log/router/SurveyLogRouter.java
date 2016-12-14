package com.atguigu.survey.log.router;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.atguigu.survey.log.thread.DataSourceKeyBinder;

/**
 * @author shuai xu
 * 2016年10月31日 下午7:32:10
 */
public class SurveyLogRouter extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		String key = DataSourceKeyBinder.getKey();
		DataSourceKeyBinder.removeKey();
		return key;
	}
	
}
