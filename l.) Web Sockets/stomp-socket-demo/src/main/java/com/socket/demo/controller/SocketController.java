package com.socket.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socket.demo.model.Notification;

@RestController
public class SocketController {
	
	@Autowired
	private SimpMessagingTemplate template;
	//For other applications to broadcast to all connected clients
	@PostMapping("/send/notification")
	public String sendNotification(@RequestBody Notification notification){		
		template.convertAndSend("/topic/notification",new Notification("Message: "+notification.getMessage()+" Notification message from REST-Endpoint at time "+System.currentTimeMillis()+ " in ms!!"));
		System.out.println("Notification sent!");
		return "Notification sent!";
	}
	//For a connected client to broadcast to other connected clients
	@MessageMapping("/notify")
	@SendTo("/topic/notification")
	public Notification notificationFromSocketClient(Notification notification) {		
		return new Notification("Message: "+notification.getMessage()+" Notification message from Socket Client at time "+System.currentTimeMillis()+ " in ms!!");
	}
	
	
	//To send to specific user
	@MessageMapping("/chat")
	@SendToUser
	public String chat(@Payload String msg) {
		return "Your Message in uppercase:- "+msg.toUpperCase();
	}
}
