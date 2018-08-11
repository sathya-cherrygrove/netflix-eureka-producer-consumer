package com.sathya.consumer.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.appinfo.InstanceInfo;

@RestController
public class consumeEmployee {

	@Autowired
	private DiscoveryClient discoveryclient;

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
			response = restTemplate.exchange(baseUrl+"/getEmployee", HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		

		 return response.getBody();
		//return "Size of the List is : "+ Integer.toString(services.size());
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}
