package com.atguigu.survey.entities.guest;

import java.io.Serializable;

/**
 * @author shuai xu
 * 2016年10月19日 下午7:25:21
 */
public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer questionId;
	private String questionName;
	private int questionType;
	private String options;
	
	private Bag bag;

	public Question() {
		super();
	}

	public Question(Integer questionId, String questionName, int questionType,
			String options, Bag bag) {
		super();
		this.questionId = questionId;
		this.questionName = questionName;
		this.questionType = questionType;
		this.options = options;
		this.bag = bag;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public int getQuestionType() {
		return questionType;
	}

	public String getOptions() {
		return options;
	}

	public Bag getBag() {
		return bag;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	//需要特殊设置方法
	public void setOptions(String options) {
		//将\r\n 替换为逗号
		this.options = options.replaceAll("\r\n", ",");
	}
	//获取选项数组
	public String[] getOptionsArr(){
		//根据逗号拆分为数组
		return this.options.split(",");
	}
	//将逗号替换为\r\n
	public String getOptionsForEdit(){
		return this.options.replaceAll(",", "\r\n");
	}
	public void setBag(Bag bag) {
		this.bag = bag;
	}
	
}
