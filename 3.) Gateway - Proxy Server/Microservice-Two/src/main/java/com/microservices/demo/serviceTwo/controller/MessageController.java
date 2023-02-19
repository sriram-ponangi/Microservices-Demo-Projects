package com.microservices.demo.serviceTwo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageController {
	
	@Value("${message.two:DEFAULT Message}")
    private String message;
	
	@GetMapping("/message")
	String myMessage() {
		return "Message Two: "+message;
	}
}