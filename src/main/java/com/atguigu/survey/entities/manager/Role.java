package com.atguigu.survey.entities.manager;

import java.util.Set;

/**
 * @author shuai xu 2016年10月25日 上午11:01:10
 */
public class Role {
	private Integer roleId;
	private String roleName;
	private Set<Auth> authSet;

	public Role() {

	}

	public Role(Integer roleId, String roleName, Set<Auth> authSet) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.authSet = authSet;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<Auth> getAuthSet() {
		return authSet;
	}

	public void setAuthSet(Set<Auth> authSet) {
		this.authSet = authSet;
	}
}
