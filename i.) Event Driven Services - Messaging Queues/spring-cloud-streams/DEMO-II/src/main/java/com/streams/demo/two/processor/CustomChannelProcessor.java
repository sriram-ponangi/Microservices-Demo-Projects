package com.streams.demo.two.processor;


import java.util.Map;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;


@EnableBinding(Processor.class)
public class CustomChannelProcessor {
	
    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Message<String> updateMessage(@Payload String payload, @Headers Map<String,Object> headers) {
    	
    	System.out.println("Priority: "+headers.get("priority")+" Processing: " + payload);
    	
    	String modifiedPayload = (payload+"____Conveted case to lower").toLowerCase();
    	System.out.println("Modified Payload: "+modifiedPayload);
    	return MessageBuilder.withPayload(modifiedPayload).setHeader("Sriram", "Ponangi").build();
    }
    
}
