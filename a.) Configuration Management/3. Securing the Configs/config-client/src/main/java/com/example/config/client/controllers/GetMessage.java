package com.example.config.client.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetMessage {
	
	@Value("${msg.type:Defualt-Message}")
	String msg;
	
	@Value("${secrets.pwd:Defualt-Secret}")
	String secret;
	
	@RequestMapping("/")
	public Map<String,String> getConfigs(){
		Map<String, String> map = new HashMap<String,String>();
		map.put("Message", msg);
		map.put("Secret", secret);
		return map;
	}
}
