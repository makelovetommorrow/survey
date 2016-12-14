package com.atguigu.survey.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

/**
 * @author shuai xu
 * 2016年10月31日 下午7:42:13
 */
public class SurveyKeyGenerator implements KeyGenerator{

	public Object generate(Object target, Method method, Object... params) {
		StringBuilder builder=new StringBuilder();
		String className = target.getClass().getName();
		String methodName = method.getName();
		builder.append(className).append(".").append(methodName);
		if(params!=null){
			for (int i = 0; i < params.length; i++) {
				Object param=params[i];
				builder.append(".").append(param);
			}
		}
		String key = builder.toString();
		
		return key;
	}

}
