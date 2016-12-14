package com.atguigu.survey.component.handler.guest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.survey.component.service.interfaces.EmployeeService;
import com.atguigu.survey.entities.guest.Employee;

@Controller
public class EmployeeHandler {
	
	public EmployeeHandler() {
		//System.err.println("EmployeeHandler对象创建了！");
	}
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/guest/employee/showList")
	public String showList(Map<String, Object> map) {
		
		List<Employee> empList = employeeService.getEmpList();
		map.put("empList", empList);
		
		// /WEB-INF/guest/employee_list.jsp
		return "guest/employee_list";
	}

}
