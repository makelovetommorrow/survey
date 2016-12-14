package com.atguigu.survey.component.dao.interfaces;

import java.util.List;

import com.atguigu.survey.base.interfaces.BaseDao;
import com.atguigu.survey.entities.guest.Employee;

/**
 * @author shuai xu 2016年10月16日 下午3:41:05
 */

public interface EmployeeDao extends BaseDao<Employee> {

	/**
	 * @return
	 */
	List<Employee> getEmpList();

}
