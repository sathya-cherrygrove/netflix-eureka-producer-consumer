package com.sathya.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeService {

	@RequestMapping("/getEmployee")
	public Employee getEmployee() {
		Employee employee = new Employee("sathya", "software engineer", 10000);
		return employee;

	}

}
