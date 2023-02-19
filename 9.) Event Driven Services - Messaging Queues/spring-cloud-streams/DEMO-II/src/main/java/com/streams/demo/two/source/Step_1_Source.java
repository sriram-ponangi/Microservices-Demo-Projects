package com.streams.demo.two.source;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

import com.streams.demo.two.models.CustomMessage;
import com.streams.demo.two.source.channel.Step_1_SourceChannel;

@EnableBinding(Step_1_SourceChannel.class)
public class Step_1_Source {

    @Bean
    @InboundChannelAdapter(channel = "step-1", poller = @Poller(fixedDelay = "40000"))
    public MessageSource<CustomMessage> step1(){
        return () -> MessageBuilder.withPayload(new CustomMessage("Hello World!!",System.currentTimeMillis())).build();
    }
}
