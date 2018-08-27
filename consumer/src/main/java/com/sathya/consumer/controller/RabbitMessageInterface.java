package com.sathya.consumer.controller;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;



public interface RabbitMessageInterface {
	
	@Output("EmployeeChannel")
    MessageChannel employeeRabbitData();

}
