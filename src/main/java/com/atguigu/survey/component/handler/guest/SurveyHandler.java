package com.atguigu.survey.component.handler.guest;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.survey.component.service.interfaces.SurveyService;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.exceptions.EditFileTooLongException;
import com.atguigu.survey.exceptions.EditFileTypeInvalidException;
import com.atguigu.survey.exceptions.RemoveSurveyFailedException;
import com.atguigu.survey.exceptions.SaveFileTooLargeException;
import com.atguigu.survey.exceptions.SaveFileTypeInvalidException;
import com.atguigu.survey.model.Page;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobalMessage;
import com.atguigu.survey.utils.GlobaleNames;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * @author shuai xu 2016年10月17日 下午7:43:00
 */
@Controller
public class SurveyHandler {
	@Autowired
	private SurveyService surveyService;
	@RequestMapping("/guest/survey/complete/{surveyId}")
	public String complete(
			@PathVariable("surveyId")Integer surveyId){
		surveyService.complete(surveyId);
		return "redirect:/index.jsp";
	}
	@RequestMapping("/guest/survey/removeSurveyDeeply/{surveyId}/{pageNo}")
	public String removeSurveyDeeply(
			@PathVariable("pageNo") Integer pageNo
			,@PathVariable("surveyId")Integer surveyId){
		surveyService.removeSurveyDeeply(surveyId);
		return "redirect:/guest/survey/showSurveyUnCompleted?pageNoStr="
				+ pageNo;
	}
	@RequestMapping("/guest/survey/toDesignUI/{surveyId}")
	public String toDesignUI(@PathVariable("surveyId")Integer surveyId
			,Map<String,Object> map){
		Survey survey = surveyService.getEntityById(surveyId);
		map.put("survey", survey);
		return "guest/survey_design";
	}
	@RequestMapping("/guest/survey/updateSurvey")
	public String updateSurvey(Survey survey,
			@RequestParam("file") MultipartFile file,
			@RequestParam("pageNo") Integer pageNo, 
			HttpServletRequest request,
			HttpSession session)
			throws Exception {
		if (!file.isEmpty()) {
			long size = file.getSize();
			if(size>102400){
				request.setAttribute("survey", survey);
				throw new EditFileTooLongException(GlobalMessage.FILE_TOO_LARGE);
			}
			String contentType = file.getContentType();
			if(!contentType.startsWith("image/")){
				request.setAttribute("survey", survey);
				throw new EditFileTypeInvalidException(GlobalMessage.FILE_TYPE_INVALID);
			}
			InputStream inputStream = file.getInputStream();
			ServletContext servletContext = session.getServletContext();
			String virtualPath = "/surveyLogos";
			String realPath = servletContext.getRealPath(virtualPath);
			String logoPath = DataprocessUtils.resizeImages(inputStream,
					realPath);
			survey.setLogoPath(logoPath);
		}
		surveyService.updateSurvey(survey);
		return "redirect:/guest/survey/showSurveyUnCompleted?pageNoStr="
				+ pageNo;
	}

	@RequestMapping("/guest/survey/toEditUI/{surveyId}/{pageNo}")
	public String toEditUI(@PathVariable("surveyId") Integer surveyId,
			@PathVariable("pageNo") Integer pageNo, Map<String, Object> map) {
		Survey survey = surveyService.getEntityById(surveyId);
		map.put("survey", survey);
		return "guest/survey_editUI";
	}

	@RequestMapping("/guest/survey/removeSurvey/{surveyId}/{pageNo}")
	public String removeSurvey(@PathVariable("surveyId") Integer surveyId,
			@PathVariable("pageNo") Integer pageNo) {
		try {
			surveyService.removeEntity(surveyId);
		} catch (Exception e) {
			Throwable cause = e.getCause();
			if(cause!=null&&cause instanceof MySQLIntegrityConstraintViolationException){
				throw new RemoveSurveyFailedException(GlobalMessage.REMOVE_SURVEY_FAILED);
			}
		}
		return "redirect:/guest/survey/showSurveyUnCompleted?pageNoStr="
				+ pageNo;
	}

	@RequestMapping("/guest/survey/showSurveyUnCompleted")
	public String showMyUncompleted(
			HttpSession session,
			@RequestParam(value = "pageNoStr", required = false) String pageNoStr,
			Map<String, Object> map) {
		User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
		Page<Survey> page = surveyService.getMyUnCompleted(user, pageNoStr);
		map.put(GlobaleNames.PAGE, page);
		return "guest/survey_myuncompleted";
	}

	/**
	 * 
	 * 保存调查
	 * 
	 * @param session
	 * @param survey
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/guest/survey/saveSurvey")
	public String saveSurvey(HttpSession session, Survey survey,
			@RequestParam("logoFile") MultipartFile file) throws Exception {
		if (!file.isEmpty()) {
			long size = file.getSize();
			// 100K限制
			if (size > 102400) {
				throw new SaveFileTooLargeException(
						GlobalMessage.FILE_TOO_LARGE);
			}
			String type = file.getContentType();
			if (!type.startsWith("image/")) {
				throw new SaveFileTypeInvalidException(
						GlobalMessage.FILE_TYPE_INVALID);
			}
			InputStream inputStream = file.getInputStream();
			ServletContext servletContext = session.getServletContext();
			String virtualPath = "/surveyLogos";
			String realPath = servletContext.getRealPath(virtualPath);
			String logoPath = DataprocessUtils.resizeImages(inputStream,
					realPath);
			survey.setLogoPath(logoPath);
		}
		User user = (User) session.getAttribute(GlobaleNames.LOGIN_USER);
		survey.setUser(user);
		surveyService.saveEntity(survey);
		/*
		 * String surveyName = survey.getSurveyName();
		 * System.out.println("surveyName:" + surveyName); String contentType =
		 * file.getContentType(); System.out.println("文件内容类型：" + contentType);
		 * String originalFilename = file.getOriginalFilename();
		 * System.out.println("文件本身的名：" + originalFilename); InputStream
		 * inputStream = file.getInputStream(); System.out.println("文件上传的输入流" +
		 * inputStream); String name = file.getName();
		 * System.out.println("文件上传框的name属性值：" + name); long size =
		 * file.getSize(); System.out.println("size:" + size);
		 */
		// ?pageNoStr=+Integer.MAX_VALUE
		return "redirect:/guest/survey/showSurveyUnCompleted";
	}
}
