package com.atguigu.survey.entities.guest;

import java.util.Set;

/**
 * @author shuai xu 2016年10月17日 下午8:05:40
 */
public class Survey {
	private Integer surveyId;
	private String surveyName;
	private boolean completed;
	private String logoPath = "res_static/logo.gif";
	private User user;
	private Set<Bag> bags;

	public Set<Bag> getBags() {
		return bags;
	}

	public void setBags(Set<Bag> bags) {
		this.bags = bags;
	}

	public Survey() {
		super();
	}

	public Survey(Integer surveyId, String surveyName, boolean completed,
			String logoPath, User user) {
		super();
		this.surveyId = surveyId;
		this.surveyName = surveyName;
		this.completed = completed;
		this.logoPath = logoPath;
		this.user = user;
	}

	/**
	 * @param surveyId2
	 */
	public Survey(Integer surveyId) {
		this.surveyId=surveyId;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public boolean isCompleted() {
		return completed;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public User getUser() {
		return user;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", surveyName=" + surveyName
				+ ", completed=" + completed + ", logoPath=" + logoPath + "]";
	}

}
