package com.streams.demo.one.sink;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.streams.demo.one.models.CustomMessage;

/*
 * Creating Subscriber using the default channel called INPUT
 */
@EnableBinding(Sink.class)
public class SimpleDefaultChannelSubscriber {

    @StreamListener(Sink.INPUT)
    public void handleDefaultMessage(CustomMessage msg) {
        System.out.println("Message via 'DEFAULT CHANNEL' Received: " + msg+"");
    }
}
