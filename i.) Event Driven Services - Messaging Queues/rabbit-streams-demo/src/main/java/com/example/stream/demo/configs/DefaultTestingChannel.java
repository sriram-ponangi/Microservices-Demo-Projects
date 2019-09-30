package com.example.stream.demo.configs;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DefaultTestingChannel {

    @Input
    MessageChannel in();

    @Output
    MessageChannel out();
}
