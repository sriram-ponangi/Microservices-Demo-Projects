package com.stream.demo.sinks.configs;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface CustomMessageSink {

    @Input("importantMessageInChannel")
    MessageChannel readImportantMessage();

    @Input("commonMessageInChannel")
    MessageChannel readCommonMessage();

}
