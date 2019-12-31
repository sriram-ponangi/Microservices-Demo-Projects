package com.microservices.demo.microserviceClient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.ServiceInstance;

@RestController
public class DiscoveryClientExampleController {
	
	@Autowired
	private DiscoveryClient discoveryClient;	
	
	RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/discoveryClient")
	String showMessage() {
		
		List<ServiceInstance> appServiceOneInstancesList=discoveryClient.getInstances("microservice-one");
		ServiceInstance appServiceOneInstances=appServiceOneInstancesList.get(0);
		String appServiceOneBaseUrl=appServiceOneInstances.getUri().toString();
		appServiceOneBaseUrl+="/one/message";
		//RestTemplate restTemplat_1 = new RestTemplate();
		String response_1 = restTemplate.getForObject(appServiceOneBaseUrl, String.class);
		
		List<ServiceInstance> appServiceTwoInstancesList=discoveryClient.getInstances("MICROSERVICE-TWO");
		ServiceInstance appServiceTwoInstances=appServiceTwoInstancesList.get(0);
		String appServiceTwoBaseUrl=appServiceTwoInstances.getUri().toString();
		appServiceTwoBaseUrl+="/two/message";
		//RestTemplate restTemplat_2 = new RestTemplate();
		String response_2 = restTemplate.getForObject(appServiceTwoBaseUrl, String.class);
		
		
		
		return response_1+"\n**********\n\n*************\n"+response_2;
		
	}
}