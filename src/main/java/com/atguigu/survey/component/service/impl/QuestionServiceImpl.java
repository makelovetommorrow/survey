package com.atguigu.survey.component.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.QuestionDao;
import com.atguigu.survey.component.service.interfaces.QuestionService;
import com.atguigu.survey.entities.guest.Question;

/**
 * @author shuai xu 2016年10月19日 下午7:43:08
 */
@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements
		QuestionService {
	@Autowired
	private QuestionDao questionDao;
}
