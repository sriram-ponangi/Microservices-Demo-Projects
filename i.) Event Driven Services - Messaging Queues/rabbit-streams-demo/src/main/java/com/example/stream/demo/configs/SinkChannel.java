package com.example.stream.demo.configs;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface SinkChannel {

    @Input("step-4")
    MessageChannel step4();
}
