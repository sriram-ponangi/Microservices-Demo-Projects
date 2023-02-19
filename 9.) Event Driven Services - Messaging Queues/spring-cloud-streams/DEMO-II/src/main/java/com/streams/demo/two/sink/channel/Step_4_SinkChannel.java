package com.streams.demo.two.sink.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface Step_4_SinkChannel {

    @Input("step-4")
    MessageChannel step4();
}
