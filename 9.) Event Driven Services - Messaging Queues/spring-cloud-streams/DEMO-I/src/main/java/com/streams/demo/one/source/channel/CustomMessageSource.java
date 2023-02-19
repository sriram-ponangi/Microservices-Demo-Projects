package com.streams.demo.one.source.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomMessageSource {

    @Output("importantMessageOutChannel")
    MessageChannel sendImportantMessage();

    @Output("commonMessageOutChannel")
    MessageChannel sendCommonMessage();
}
