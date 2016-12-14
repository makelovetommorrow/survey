package com.atguigu.survey.component.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.AnswerDao;
import com.atguigu.survey.component.dao.interfaces.SurveyDao;
import com.atguigu.survey.component.service.interfaces.EngageService;
import com.atguigu.survey.entities.guest.Answer;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.utils.DataprocessUtils;

/**
 * @author shuai xu 2016年10月21日 下午8:14:39
 */
@Service
public class EngageServiceImpl extends BaseServiceImpl<Survey> implements
		EngageService {
	@Autowired
	private SurveyDao surveyDao;
	@Autowired
	private AnswerDao answerDao;
	public void parseAndSave(Map<Integer, Map<String, String[]>> allBagMap,
			Integer surveyId) {
		// 0.生成代表当前批次的UUID值
		String uuid = UUID.randomUUID().toString();

		// 1.解析
		List<Answer> answerList = new ArrayList<Answer>();

		// 由于解析答案时并不关心这个答案属于哪一个包裹，所以遍历allBagMap的值的集合即可
		Collection<Map<String, String[]>> values = allBagMap.values();

		for (Map<String, String[]> paramMap : values) {
			Set<Entry<String, String[]>> entrySet = paramMap.entrySet();

			for (Entry<String, String[]> entry : entrySet) {
				// q23=[xxx,xxx]
				// 键：请求参数名
				String key = entry.getKey();
				System.out.println("key"+key);
				// 值：请求参数值
				String[] value = entry.getValue();
				for (String string : value) {
					System.out.println("content"+string);
				}
				// 检查key是否是以q开头的，如果是说明是要解析的答案数据，否则不进行任何处理
				if (!key.startsWith("q")) {
					continue;
				}

				// 从key中解析出questionId
				Integer questionId = DataprocessUtils.parseQuestionId(key);

				// 将value从数组转换成字符串
				String content = DataprocessUtils.convertArr2Str(value);

				Answer answer = new Answer(null, content, uuid, surveyId,
						questionId);
				answerList.add(answer);
			}

		}
		// 2.保存
		answerDao.batchSaveAnswerList(answerList);
	}
}
