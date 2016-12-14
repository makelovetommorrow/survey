package com.atguigu.survey.log.thread;

/**
 * @author shuai xu
 * 2016年10月31日 下午6:22:35
 */
public class DataSourceKeyBinder {
	private static ThreadLocal<String> local=new ThreadLocal<String>();
	public static final String DATASOURCE_LOG_KEY="DATASOURCE_LOG";
	public static void bindKey(String key){
		local.set(key);
	}
	public static void removeKey(){
		local.remove();
	}
	public static String getKey(){
		return local.get();
	}
}
