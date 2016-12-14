package com.atguigu.survey.component.dao.interfaces;

import java.util.Set;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.entities.guest.Question;

/**
 * @author shuai xu
 * 2016年10月19日 下午7:45:58
 */
public interface QuestionDao extends BaseDao<Question>{

	/**
	 * @param questions
	 */
	void batchSaveQuestion(Set<Question> questions);

}
