package com.streams.demo.two.processor.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomChannels {

    @Input
    MessageChannel in();

    @Output
    MessageChannel out();
}
