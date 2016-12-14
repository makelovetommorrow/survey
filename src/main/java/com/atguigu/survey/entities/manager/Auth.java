package com.atguigu.survey.entities.manager;

import java.util.Set;

/**
 * @author shuai xu 2016年10月25日 上午11:01:01
 */
public class Auth {
	private Integer authId;
	private String authName;
	private Set<Res> resSet;

	public Auth() {
		// TODO Auto-generated constructor stub
	}

	public Auth(Integer authId, String authName, Set<Res> resSet) {
		super();
		this.authId = authId;
		this.authName = authName;
		this.resSet = resSet;
	}

	@Override
	public String toString() {
		return "Auth [authId=" + authId + ", authName=" + authName + "]";
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public Set<Res> getResSet() {
		return resSet;
	}

	public void setResSet(Set<Res> resSet) {
		this.resSet = resSet;
	}

}
