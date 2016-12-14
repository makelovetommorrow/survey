package com.atguigu.survey.component.handler.manager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.interfaces.StatisticsService;
import com.atguigu.survey.component.service.interfaces.SurveyService;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.model.Page;
import com.atguigu.survey.utils.GlobaleNames;

@Controller
public class StatisticsHandler {
	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private SurveyService surveyService;

	@RequestMapping("/manager/statistics/exportExcel/{surveyId}")
	public void exportExcel(@PathVariable("surveyId") Integer surveyId,
			HttpServletResponse response) throws IOException {
		HSSFWorkbook workBook = statisticsService.generateWorkBook(surveyId);
		response.setContentType("application/vnd.ms-excel");
		String fileName = System.nanoTime() + ".xls";
		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName);
		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
	}

	@RequestMapping("/manager/statistics/showChart/{questionId}")
	public void showChart(@PathVariable("questionId") Integer questionId,
			HttpServletResponse response) throws IOException {
		JFreeChart chart = statisticsService.generateChart(questionId);
		ServletOutputStream outputStream = response.getOutputStream();
		ChartUtilities.writeChartAsJPEG(outputStream, chart, 800, 600);
	}

	@RequestMapping("/manager/statistics/showSimpleList/{questionId}")
	public String showSimpleList(
			@PathVariable("questionId") Integer questionId,
			Map<String, Object> map) {
		List<String> contentList = statisticsService
				.getSimpleContentList(questionId);
		map.put("contentList", contentList);
		return "manager/statistics_simple_list";
	}

	@RequestMapping("/manager/statistics/showSummary/{surveyId}")
	public String showSummary(@PathVariable("surveyId") Integer surveyId,
			Map<String, Object> map) {
		Survey survey = surveyService.getEntityById(surveyId);
		map.put("survey", survey);
		return "manager/statistics_summary";
	}

	@RequestMapping("/manager/statistics/showAllAvailable")
	public String showAllAvailable(
			@RequestParam(value = "pageNoStr", required = false) String pageNoStr,
			Map<String, Object> map) {
		Page<Survey> page = surveyService.getAllAvailable(pageNoStr);
		map.put(GlobaleNames.PAGE, page);
		return "manager/statistics_list";
	}

}
