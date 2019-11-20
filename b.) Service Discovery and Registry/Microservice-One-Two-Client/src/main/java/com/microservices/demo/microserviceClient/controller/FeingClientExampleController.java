package com.microservices.demo.microserviceClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.demo.microserviceClient.feing.MicroserviceOne;
import com.microservices.demo.microserviceClient.feing.MicroserviceTwo;

@RestController
public class FeingClientExampleController{
	
	@Autowired
	private MicroserviceOne serviceOneClient;
	
	@Autowired
	private MicroserviceTwo serviceTwoClient;	
	
	@GetMapping("/feingClient")
	String showMessage() {
		return serviceOneClient.getServiceOneMessage()+"\n--------------\n\n-----------\n"+serviceTwoClient.getServiceTwoMessage();
		
	}
}