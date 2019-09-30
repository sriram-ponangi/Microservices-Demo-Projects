package com.example.stream.demo.processor;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;


@EnableBinding(Processor.class)
public class DefaultChannelProcessor {

//    @StreamListener(Processor.INPUT)
//    @SendTo(Processor.OUTPUT)
//    public String updateMessage(String msg) {
//        System.out.println("Processing: " + msg);
//        return msg.toLowerCase()+"____Conveted to lowercase___";
//    }
}
