package com.atguigu.survey.entities.guest;

import java.io.Serializable;
import java.util.Set;

/**
 * @author shuai xu 2016年10月19日 上午11:34:22
 */
public class Bag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer bagId;
	private String bagName;
	private int bagOrder;
	private transient Survey survey;
	private Set<Question> questions;
	
	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Bag(Integer bagId, String bagName, int bagOrder, Survey survey) {
		super();
		this.bagId = bagId;
		this.bagName = bagName;
		this.bagOrder = bagOrder;
		this.survey = survey;
	}

	public Bag() {
		super();
	}

	public Integer getBagId() {
		return bagId;
	}

	public String getBagName() {
		return bagName;
	}

	public int getBagOrder() {
		return bagOrder;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}

	public void setBagName(String bagName) {
		this.bagName = bagName;
	}

	public void setBagOrder(int bagOrder) {
		this.bagOrder = bagOrder;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	@Override
	public String toString() {
		return "Bag [bagId=" + bagId + ", bagName=" + bagName + ", bagOrder="
				+ bagOrder + "]";
	}

}
