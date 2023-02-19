package com.streams.demo.two.processor;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import com.streams.demo.two.models.CustomMessage;
import com.streams.demo.two.processor.channel.Step_2_3_ProcessorChannel;


@EnableBinding(Step_2_3_ProcessorChannel.class)
public class Step_2_3_Processor {


    @StreamListener("step-2")
    @SendTo("step-3")
    public CustomMessage step2AndStep3(CustomMessage msg) {
        System.out.println("\nIncoming Step-2 Msg: "+msg);
        msg.setTime(System.currentTimeMillis() - msg.getTime());
        return msg;
    }
}

