package com.atguigu.survey.component.service.impl;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.AnswerDao;
import com.atguigu.survey.component.dao.interfaces.QuestionDao;
import com.atguigu.survey.component.service.interfaces.StatisticsService;
import com.atguigu.survey.entities.guest.Answer;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.entities.guest.Survey;

/**
 * @author shuai xu
 * 2016年10月24日 下午4:15:36
 */
@Service
public class StatisticsServiceImpl extends BaseServiceImpl<Survey> implements StatisticsService{
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private AnswerDao answerDao;
	public List<String> getSimpleContentList(Integer questionId) {
		
		return answerDao.getSimpleContentList(questionId);
	}
	public JFreeChart generateChart(Integer questionId) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		Question question = questionDao.getEntityById(questionId);
		int questionEngagedCount=answerDao.getQuestionEngagedCount(questionId);
		String questionName = question.getQuestionName();
		String title=questionName+"【"+questionEngagedCount+"】次参与";
		String[] optionsArr = question.getOptionsArr();
		for (int i = 0; i < optionsArr.length; i++) {
			int optionEngagedCount=answerDao.getOptionEngagedCount(questionId,i);
			String option=optionsArr[i];
			dataset.setValue(option, optionEngagedCount);
		}
		JFreeChart freeChart = ChartFactory.createPieChart(title, dataset);
		Font titleFont = new Font("宋体", Font.PLAIN, 30);
		freeChart.getTitle().setFont(titleFont);
		Font legendFont = new Font("宋体", Font.PLAIN, 30);
		freeChart.getLegend().setItemFont(legendFont);
		Font labelFont = new Font("宋体", Font.PLAIN, 20);
		PiePlot plot = (PiePlot) freeChart.getPlot();
		plot.setLabelFont(labelFont);
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}/{3},{2}"));
		plot.setForegroundAlpha(0.6f);
		return freeChart;
	}
	/**
	 * 管理员时生成工作表的方法
	 */
	public HSSFWorkbook generateWorkBook(Integer surveyId) {
		Survey survey = getEntityById(surveyId);
		int surveyEngagedCount=answerDao.getSurveyEngagedCount(surveyId);
		String surveyName = survey.getSurveyName();
		String sheetName=surveyName+"【"+surveyEngagedCount+"】次参与";
		List<Question> questionList=new ArrayList<Question>();
		Set<Bag> bags = survey.getBags();
		for (Bag bag : bags) {
			Set<Question> questions = bag.getQuestions();
			questionList.addAll(questions);
		}
		List<Answer> answerList = answerDao.getListBySurveyId(surveyId);
		Map<String, Map<Integer, String>> bigMap = generateBigMap(answerList);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);
		HSSFRow firstRow = sheet.createRow(0);
		for (int i = 0; i < questionList.size(); i++) {
			Question question = questionList.get(i);
			String questionName = question.getQuestionName();
			HSSFCell questionNameCell = firstRow.createCell(i);
			questionNameCell.setCellValue(questionName);
		}
		int j =1;
		Set<String> keySet = bigMap.keySet();
		for (String uuid : keySet) {
			Map<Integer, String> smallMap = bigMap.get(uuid);
			HSSFRow row = sheet.createRow(j);
			for (int k = 0; k < questionList.size(); k++) {
				HSSFCell cell = row.createCell(k);
				Question question = questionList.get(k);
				Integer questionId = question.getQuestionId();
				String content = smallMap.get(questionId);
				cell.setCellValue(content);
			}
			j++;
		}
		return workbook;
	}
	public Map<String,Map<Integer,String>> generateBigMap(List<Answer> answerList){
		Map<String,Map<Integer,String>> bigMap=new HashMap<String, Map<Integer, String>>();
		for (Answer answer : answerList) {
			String uuid = answer.getUuid();
			String content = answer.getContent();
			Integer questionId = answer.getQuestionId();
			Map<Integer, String> smallMap = bigMap.get(uuid);
			if(smallMap==null){
				smallMap=new HashMap<Integer, String>();
				bigMap.put(uuid, smallMap);
			}
			smallMap.put(questionId, content);
		}
		return bigMap;
	}
	
}
