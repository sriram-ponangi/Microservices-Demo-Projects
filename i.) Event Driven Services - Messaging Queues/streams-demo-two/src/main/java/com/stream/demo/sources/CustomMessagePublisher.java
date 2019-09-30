package com.stream.demo.sources;

import com.stream.demo.models.CustomMessage;
import com.stream.demo.sources.configs.CustomMessageSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

import java.util.Random;


@EnableBinding(CustomMessageSource.class)
public class CustomMessagePublisher {

    Random random = new Random();

    @Bean
    @InboundChannelAdapter(channel = "importantMessageOutChannel", poller = @Poller(fixedDelay = "2000"))
    public MessageSource<CustomMessage> sendImportantMessage(){
        return () -> {
            int num = random.nextInt(60);
            return MessageBuilder.withPayload(new CustomMessage("vip message!!!!!", "Form..."+num))
                    .setHeader("priority", num)
                    .build();
        };
    }

    @Bean
    @InboundChannelAdapter(channel = "commonMessageOutChannel",  poller = @Poller(fixedDelay = "20000"))
    public MessageSource<CustomMessage> sendcommonMessage(){
        return () -> MessageBuilder.withPayload(new CustomMessage("common message.....", "Form Sriram")).build();
    }
}
