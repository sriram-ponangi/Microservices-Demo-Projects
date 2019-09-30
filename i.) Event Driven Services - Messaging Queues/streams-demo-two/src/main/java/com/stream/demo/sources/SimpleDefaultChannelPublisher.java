package com.stream.demo.sources;

import com.stream.demo.models.CustomMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(Source.class)
public class SimpleDefaultChannelPublisher {

    @Bean
    @InboundChannelAdapter(channel = Source.OUTPUT)
    public MessageSource<CustomMessage> sendDefaultMessage(){
        return () -> MessageBuilder.withPayload(new CustomMessage("vA P J Abdul Kalam", "____FROM-DEFAULT___")).build();
    }


}
