package com.socket.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements  WebSocketMessageBrokerConfigurer  {

	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/socket")
		.setAllowedOrigins("*")
		.withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//clients subscribe to end-points with this prefix
		//We are using a in memory queue hence previous messages won't be available
		registry.enableSimpleBroker("/topic","/queue");
		
		//for the client to send data it should use end-points with this prefix
		//transfers the program control to a handler (@MessageMapping) method
 		registry.setApplicationDestinationPrefixes("/app");
 		
 		//For clients to receive data back without broadcasting
		registry.setUserDestinationPrefix("/user");
		
	}
}
