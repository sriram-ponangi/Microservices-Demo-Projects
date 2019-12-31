package com.microservices.demo.microserviceClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonClientExampleController {

	@Autowired
	private LoadBalancerClient loadBalancer;
	
	//@Autowired
	RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/ribbonClient")
	String showMessage() {		
		
		ServiceInstance appServiceOneInstance= loadBalancer.choose("microservice-one");		
		String appServiceOneBaseUrl= appServiceOneInstance.getUri().toString();		
		appServiceOneBaseUrl+="/one/message";		
		System.out.println(appServiceOneBaseUrl);
		//RestTemplate restTemplat_1 = new RestTemplate();
		String response_1 = restTemplate.getForObject(appServiceOneBaseUrl, String.class);
		
		
		ServiceInstance appServiceTwoInstance= loadBalancer.choose("MICROSERVICE-TWO");		
		String appServiceTwoBaseUrl= appServiceTwoInstance.getUri().toString();	
		appServiceTwoBaseUrl+="/two/message";
		System.out.println(appServiceTwoBaseUrl);
		//RestTemplate restTemplat_2 = new RestTemplate();
		String response_2 = restTemplate.getForObject(appServiceTwoBaseUrl, String.class);
		
		
		
		return response_1+"\n++++++++++\n\n+++++++++++++n"+response_2;
		
	}
}