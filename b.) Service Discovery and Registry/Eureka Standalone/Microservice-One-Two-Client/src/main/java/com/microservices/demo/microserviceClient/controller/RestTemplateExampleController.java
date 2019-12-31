package com.microservices.demo.microserviceClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateExampleController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/restTemplateClient")
	String showMessage() {
		String urlOne = "http://MICROSERVICE-ONE/one/message";
		String response_1 = restTemplate.getForObject(urlOne, String.class);		
		
		String urlTwo = "http://MICROSERVICE-TWO/two/message";
		String response_2 = restTemplate.getForObject(urlTwo, String.class);
		
		return response_1+"**********-------------**********"+response_2;
	}
	
}
