package com.streams.demo.two.controllers;

import java.util.Random;


import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.messaging.Source;
import com.streams.demo.two.processor.channel.CustomChannels;

@RestController
public class SendMessageController {
	
//	@Autowired
//	Source defaulSource;
	
	@Autowired
	CustomChannels customSource;

	@PostMapping("/message")
	public String sendDefaultMessage(@RequestBody String payload) {	
//		defaulSource.output().send( MessageBuilder.withPayload(payload).setHeader("priority", new Random().nextInt(100) ).build() );
		customSource.out().send( MessageBuilder.withPayload(payload).setHeader("priority", new Random().nextInt(100) ).build() );
		
		return "Message sucessfully added in queue...";
	}

}
