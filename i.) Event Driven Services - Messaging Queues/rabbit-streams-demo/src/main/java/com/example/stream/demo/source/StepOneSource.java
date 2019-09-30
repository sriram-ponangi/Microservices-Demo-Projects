package com.example.stream.demo.source;

import com.example.stream.demo.configs.SourceChannel;
import com.example.stream.demo.models.CustomMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(SourceChannel.class)
public class StepOneSource {

    @Bean
    @InboundChannelAdapter(channel = "step-1", poller = @Poller(fixedDelay = "2000"))
    public MessageSource<CustomMessage> step1(){
        return () -> MessageBuilder.withPayload(new CustomMessage("Hello Sriram",System.currentTimeMillis())).build();
    }


}
