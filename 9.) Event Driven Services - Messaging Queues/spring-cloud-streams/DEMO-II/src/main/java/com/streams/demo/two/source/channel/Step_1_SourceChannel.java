package com.streams.demo.two.source.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Step_1_SourceChannel {

    @Output("step-1")
    MessageChannel step1();
}
