package com.atguigu.survey.component.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.impl.BaseDaoImpl;
import com.atguigu.survey.component.dao.interfaces.EmployeeDao;
import com.atguigu.survey.entities.guest.Employee;

/**
 * @author shuai xu
 * 2016年10月16日 下午3:43:47
 */
@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {

	/**
	 * 
	 */
	public EmployeeDaoImpl() {
		
	}

	public List<Employee> getEmpList() {
		String hql = "From Employee e where e.empId<?";
		return getEntityListByHql(hql, 50);
	}
}
