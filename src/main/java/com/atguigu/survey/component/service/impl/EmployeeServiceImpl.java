package com.atguigu.survey.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.impl.BaseServiceImpl;
import com.atguigu.survey.component.dao.interfaces.EmployeeDao;
import com.atguigu.survey.component.service.interfaces.EmployeeService;
import com.atguigu.survey.entities.guest.Employee;

/**
 * @author shuai xu 2016年10月16日 下午5:44:24
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements
		EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	public List<Employee> getEmpList() {
		return employeeDao.getEmpList();
	}

}
