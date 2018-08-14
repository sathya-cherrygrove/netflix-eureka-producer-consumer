package com.sathya.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class EmployeeService {

	@RequestMapping("/getEmployee")
	@HystrixCommand(fallbackMethod = "fallback")
	public Employee getEmployee() throws Exception {
		Employee employee = new Employee("sathya", "software engineer", 10000);
		return employee;

	}

	public Employee fallback() {
		Employee emp = new Employee();
		emp.setName("Fallback Name");
		emp.setDesignation("boss man");
		emp.setSal(20000);
		return emp;

	}

}
