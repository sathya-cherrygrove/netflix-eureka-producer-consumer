package com.sathya.consumer.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.appinfo.InstanceInfo;

@RestController
@EnableBinding(RabbitMessageInterface.class)
public class consumeEmployee {

	@Autowired
	private DiscoveryClient discoveryclient;

	@Autowired
	private LoadBalancerClient loadBalancer;
	
	@Autowired
	private RabbitMessageInterface rabbitmessageinterface;

	@RequestMapping("/consumeProducer")
	public String consumeEmployee() {

		String baseUrl = "";
		List<ServiceInstance> services = discoveryclient.getInstances("producer");
		for (ServiceInstance service : services) {
			System.out.println("Url : " + service.getUri().toString());
			System.out.println("Port : " + service.getPort());
			System.out.println("Port : " + service.getServiceId());
			baseUrl = service.getUri().toString();

		}

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl + "/getEmployee", HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return response.getBody();
		// return "Size of the List is : "+ Integer.toString(services.size());
	}

	@RequestMapping("/loadConsumeProducer")
	public String loadConsumeProducer() {
		ServiceInstance serviceInstance = loadBalancer.choose("producer");
		System.out.println("URI : " + serviceInstance.getUri());
		System.out.println("Host : " + serviceInstance.getHost());
		System.out.println("Service Id : " + serviceInstance.getServiceId());
		String baseUrl = serviceInstance.getUri().toString();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return response.getBody();

	}

	@RequestMapping("/talkToZuul")
	public String talkToZuul() {
		System.out.println("Talk to Zuul Called");
		List<ServiceInstance> instances = discoveryclient.getInstances("netflix-zuul");
		ServiceInstance serviceInstance = instances.get(0);
		System.out.println("Calling Service : " + serviceInstance.getUri());
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/producer/getEmployee";
		System.out.println("Base Url : " + baseUrl);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return response.getBody();
	}

	private static HttpEntity<?> getHeaders() throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);

	}

	@RequestMapping("/sendRabbitMessage")
	public String sendDataToRabbit(@RequestBody Employee employee)
	{
		
		rabbitmessageinterface.employeeRabbitData().send(MessageBuilder.withPayload(employee).build());
		System.out.println(employee);
		return "Message Sent to Rabbit MQ";
	}

}
