package com.example.stream.demo.processor;

import com.example.stream.demo.configs.ProcessorChannel;
import com.example.stream.demo.models.CustomMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;


@EnableBinding(ProcessorChannel.class)
public class IntermediateStepProcessor {


    @StreamListener("step-2")
    @SendTo("step-3")
    public CustomMessage step2AndStep3(CustomMessage msg) {
        System.out.println("\nIncoming Step-2 Msg: "+msg);
        msg.setTime(System.currentTimeMillis() - msg.getTime());
        return msg;
    }
}

