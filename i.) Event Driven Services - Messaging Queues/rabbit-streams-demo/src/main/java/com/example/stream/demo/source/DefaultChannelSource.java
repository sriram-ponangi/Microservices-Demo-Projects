package com.example.stream.demo.source;

import com.example.stream.demo.configs.DefaultTestingChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(DefaultTestingChannel.class)
public class DefaultChannelSource {

    @Bean
//    @InboundChannelAdapter(channel = "out")
    @InboundChannelAdapter(channel = "out", poller = @Poller(fixedDelay = "30000"))
    public MessageSource<String> sendMessage(){
        return () -> MessageBuilder.withPayload( "Sriram Ponangi" ).build();
    }

}
