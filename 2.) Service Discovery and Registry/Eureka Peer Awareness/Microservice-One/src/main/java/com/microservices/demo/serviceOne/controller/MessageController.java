package com.microservices.demo.serviceOne.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/one")
@RefreshScope
public class MessageController {
	
	@Value("${message.one:DEFAULT Message}")
    private String message;	
	
	@Value("${eureka.instance.instance-id:UNKOWN}")
	private String instanceId;	
	
	@GetMapping("/message")
	String myMessage() {
		return "Message one: "+message + " and with Instance id : "+instanceId;
	}
}