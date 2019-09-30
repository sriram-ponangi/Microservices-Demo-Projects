package com.example.stream.demo.configs;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SourceChannel {

    @Output("step-1")
    MessageChannel step1();
}
