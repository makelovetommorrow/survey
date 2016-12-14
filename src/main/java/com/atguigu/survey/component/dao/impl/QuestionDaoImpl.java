package com.atguigu.survey.component.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.impl.BaseDaoImpl;
import com.atguigu.survey.component.dao.interfaces.QuestionDao;
import com.atguigu.survey.entities.guest.Question;

/**
 * @author shuai xu 2016年10月19日 下午7:46:42
 */
@Repository
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements
		QuestionDao {

	public void batchSaveQuestion(Set<Question> questions) {
		int size = questions.size();
		String sql="INSERT INTO bag_question(QUESTION_NAME,QUESTION_TYPE,QUESTION_OPTIONS,BAG_ID) VALUES(?,?,?,?)";
		Object[][] params=new Object[size][4];
		List<Question> questionList=new ArrayList<Question>(questions);
		for(int i=0;i<size;i++){
			Question question = questionList.get(i);
			String questionName = question.getQuestionName();
			int questionType = question.getQuestionType();
			String options = question.getOptions();
			Integer bagId = question.getBag().getBagId();
			params[i]=new Object[]{questionName,questionType,options,bagId};
		}
		batchUpdate(sql, params);
	}

}
