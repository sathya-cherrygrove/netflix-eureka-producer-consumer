package com.sathya.producer.controller;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

//@EnableBinding(Sink.class)
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
	
	@RequestMapping("/getRabbitMessage")
	@StreamListener(value = Sink.INPUT)
	public void processRegisterEmployees(String employee) {
		System.out.println("Employees Registered by Client " + employee);
	}

}
