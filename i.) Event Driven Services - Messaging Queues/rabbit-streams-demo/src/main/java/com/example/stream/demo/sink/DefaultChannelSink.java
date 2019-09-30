package com.example.stream.demo.sink;


import com.example.stream.demo.configs.DefaultTestingChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(DefaultTestingChannel.class)
public class DefaultChannelSink {

//    @StreamListener("in")
//    public void displlayMessage(String msg) {
//        System.out.println("DEFAULT SINK: "+msg.toUpperCase());
//    }
}