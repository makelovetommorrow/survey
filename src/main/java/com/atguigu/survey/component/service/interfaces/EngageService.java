package com.atguigu.survey.component.service.interfaces;

import java.util.Map;

import com.atguigu.survey.base.interfaces.BaseService;
import com.atguigu.survey.entities.guest.Survey;

/**
 * @author shuai xu
 * 2016年10月21日 下午8:13:57
 */
public interface EngageService extends BaseService<Survey> {

	/**
	 * @param allBagMap
	 * @param surveyId
	 */
	void parseAndSave(Map<Integer, Map<String, String[]>> allBagMap,
			Integer surveyId);

}
