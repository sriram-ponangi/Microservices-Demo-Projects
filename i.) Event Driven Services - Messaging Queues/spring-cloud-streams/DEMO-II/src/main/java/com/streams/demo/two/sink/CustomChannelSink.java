package com.streams.demo.two.sink;

import java.util.Map;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import com.streams.demo.two.processor.channel.CustomChannels;

@EnableBinding(CustomChannels.class)
public class CustomChannelSink {

    @StreamListener("in")
    public void displayMessage(@Payload String msg, @Headers Map<String,Object> headers) {
        System.out.println("Final message from SINK: " + msg.substring(0, msg.length()-8).toUpperCase());
        System.out.println("Headers->Sriram: "+headers.get("Sriram"));
        System.out.println("Headers->Priority: "+headers.get("priority"));
    }
}