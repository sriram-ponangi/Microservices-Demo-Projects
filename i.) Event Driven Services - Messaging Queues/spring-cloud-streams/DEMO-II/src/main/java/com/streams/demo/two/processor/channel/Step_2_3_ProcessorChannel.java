package com.streams.demo.two.processor.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Step_2_3_ProcessorChannel {
    @Input("step-2")
    MessageChannel step2();

    @Output("step-3")
    MessageChannel step3();
}



