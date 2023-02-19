package com.streams.demo.one.source;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

import com.streams.demo.one.models.CustomMessage;
import com.streams.demo.one.source.channel.CustomMessageSource;

import java.util.Random;

/*
 * Creating Subscriber using the custom channel importantMessageOutChannel and commonMessageOutChannel
 */
@EnableBinding(CustomMessageSource.class)
public class CustomMessagePublisher {    

	/*
	 * Pushing a new message for every 20 seconds
	 * To the destination topic/exchange of the channel importantMessageOutChannel
	 * Destination topic will be given in the application.yml
	 */
    @Bean
    @InboundChannelAdapter(channel = "importantMessageOutChannel", poller = @Poller(fixedDelay = "20000"))
    public MessageSource<CustomMessage> sendImportantMessage(){
        return () -> {
        	
            int num = new Random().nextInt(60);
            return MessageBuilder.withPayload(new CustomMessage("important message!!!!! --> PRIORITY="+num))
                    .setHeader("priority", num)
                    .build();
        };
    }
    
    /*
	 * Pushing a new message for every 2 seconds
	 * To the destination topic/exchange of the channel commonMessageOutChannel
	 * Destination topic will be given in the application.yml
	 */
    @Bean
    @InboundChannelAdapter(channel = "commonMessageOutChannel",  poller = @Poller(fixedDelay = "2000"))
    public MessageSource<CustomMessage> sendcommonMessage(){
        return () -> MessageBuilder.withPayload(new CustomMessage("common message.....")).build();
    }
}
