package com.example.stream.demo.configs;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProcessorChannel {
    @Input("step-2")
    MessageChannel step2();

    @Output("step-3")
    MessageChannel step3();
}



