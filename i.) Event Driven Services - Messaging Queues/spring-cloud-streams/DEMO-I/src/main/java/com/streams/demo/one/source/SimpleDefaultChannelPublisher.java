package com.streams.demo.one.source;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

import com.streams.demo.one.models.CustomMessage;
/*
 * Creating Publisher using the default channel called OUTPUT
 */
@EnableBinding(Source.class)
public class SimpleDefaultChannelPublisher {

    @Bean
    @InboundChannelAdapter(channel = Source.OUTPUT)
    public MessageSource<CustomMessage> sendDefaultMessage(){
    	return () -> MessageBuilder.withPayload(new CustomMessage("Message via default channel",System.currentTimeMillis())).build();    	      
    }
}
