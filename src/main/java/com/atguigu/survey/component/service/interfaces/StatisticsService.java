package com.atguigu.survey.component.service.interfaces;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.JFreeChart;

import com.atguigu.survey.base.interfaces.BaseService;
import com.atguigu.survey.entities.guest.Survey;

/**
 * @author shuai xu
 * 2016年10月24日 下午4:13:57
 */
public interface StatisticsService extends BaseService<Survey>{

	/**
	 * @param questionId
	 * @return
	 */
	List<String> getSimpleContentList(Integer questionId);

	/**
	 * @param questionId
	 * @return
	 */
	JFreeChart generateChart(Integer questionId);
	
	HSSFWorkbook generateWorkBook(Integer surveyId);
}
