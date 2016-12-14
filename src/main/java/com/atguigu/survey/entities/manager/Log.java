package com.atguigu.survey.entities.manager;

public class Log {
	
	//①ID
	private Integer logId;
	
	//②操作人
	private String operator;
	
	//③操作时间
	private String operateTime;
	
	//④方法名
	private String methodName;
	
	//⑤方法所在的类型
	private String typeName;
	
	//⑥参数列表
	private String paramList;
	
	//⑦返回值
	private String returnValue;
	
	//⑧异常类型
	private String exceptionType;
	
	//⑨异常消息
	private String exceptionMessage;
	
	public Log() {
		
	}

	public Log(Integer logId, String operator, String operateTime,
			String methodName, String typeName, String paramList,
			String returnValue, String exceptionType, String exceptionMessage) {
		super();
		this.logId = logId;
		this.operator = operator;
		this.operateTime = operateTime;
		this.methodName = methodName;
		this.typeName = typeName;
		this.paramList = paramList;
		this.returnValue = returnValue;
		this.exceptionType = exceptionType;
		this.exceptionMessage = exceptionMessage;
	}

	@Override
	public String toString() {
		return "Log [logId=" + logId + ", operator=" + operator
				+ ", operateTime=" + operateTime + ", methodName=" + methodName
				+ ", typeName=" + typeName + ", paramList=" + paramList
				+ ", returnValue=" + returnValue + ", exceptionType="
				+ exceptionType + ", exceptionMessage=" + exceptionMessage
				+ "]";
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getParamList() {
		return paramList;
	}

	public void setParamList(String paramList) {
		this.paramList = paramList;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
