package com.atguigu.survey.component.handler.manager;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.interfaces.LogService;
import com.atguigu.survey.entities.manager.Log;
import com.atguigu.survey.log.thread.DataSourceKeyBinder;
import com.atguigu.survey.model.Page;
import com.atguigu.survey.utils.GlobaleNames;

/**
 * @author shuai xu
 * 2016年10月31日 下午1:04:38
 */
@Controller
public class LogHandler {
	@Autowired
	private LogService logService;
	@RequestMapping("/manager/log/showList")
	public String showList(
			@RequestParam(value="pageNoStr",required=false)String pageNoStr
			,Map<String,Object> map){
		DataSourceKeyBinder.bindKey(DataSourceKeyBinder.DATASOURCE_LOG_KEY);
		Page<Log> page = logService.getPage(pageNoStr);
		map.put(GlobaleNames.PAGE, page);
		return "manager/log_showList";
	}
}
