package com.streams.demo.one.sink.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface CustomMessageSink {

    @Input("importantMessageInChannel")
    MessageChannel readImportantMessage();

    @Input("commonMessageInChannel")
    MessageChannel readCommonMessage();

}
