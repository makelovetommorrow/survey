package com.atguigu.survey.component.service.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseService;
import com.atguigu.survey.entities.guest.Employee;

/**
 * @author shuai xu 2016年10月16日 下午5:43:13
 */
public interface EmployeeService extends BaseService<Employee> {
	List<Employee> getEmpList();
}
