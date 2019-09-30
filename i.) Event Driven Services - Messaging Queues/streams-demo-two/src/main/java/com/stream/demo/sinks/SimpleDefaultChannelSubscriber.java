package com.stream.demo.sinks;

import com.stream.demo.models.CustomMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class SimpleDefaultChannelSubscriber {

    @StreamListener(Sink.INPUT)
    public void handleDefaultMessage(CustomMessage msg) {
        System.out.println("\n\nReceived: " + msg+"\n\n");
    }
}
