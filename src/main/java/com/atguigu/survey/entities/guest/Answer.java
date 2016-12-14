package com.atguigu.survey.entities.guest;

/**
 * @author shuai xu 2016年10月23日 下午8:40:27
 */
public class Answer {
	private Integer answerId;
	private String content;
	private String uuid;
	private Integer surveyId;
	private Integer questionId;

	public Answer(Integer answerId, String content, String uuid,
			Integer surveyId, Integer questionId) {
		super();
		this.answerId = answerId;
		this.content = content;
		this.uuid = uuid;
		this.surveyId = surveyId;
		this.questionId = questionId;
	}

	public Answer() {
		super();
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public String getContent() {
		return content;
	}

	public String getUuid() {
		return uuid;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", content=" + content
				+ ", uuid=" + uuid + ", surveyId=" + surveyId + ", questionId="
				+ questionId + "]";
	}

}
