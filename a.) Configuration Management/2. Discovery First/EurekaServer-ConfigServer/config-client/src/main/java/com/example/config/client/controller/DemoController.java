package com.example.config.client.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.client.model.Mail;

@RestController
public class DemoController {
	
	@Autowired
	private Mail mailInfo;
	
	@Value("${message.one: DEFAULT MESSAGE **ONE**}")
	private String messageOne;
	
	@Value("${message.two: DEFAULT MESSAGE **TWO**}")
	private String messageTwo;
	
	

	@GetMapping("/mail")
	public String getMailInfo() {		
		return mailInfo.toString();
	}	
		
	@GetMapping("/message")
	public ArrayList<String> getMessage() {
		ArrayList<String> messages = new ArrayList<String>();
		messages.add(messageOne);
		messages.add(messageTwo);
		return messages;
	}
	
}
