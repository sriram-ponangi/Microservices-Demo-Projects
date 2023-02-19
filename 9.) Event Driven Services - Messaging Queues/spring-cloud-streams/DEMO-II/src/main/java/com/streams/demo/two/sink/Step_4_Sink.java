package com.streams.demo.two.sink;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.streams.demo.two.models.CustomMessage;
import com.streams.demo.two.sink.channel.Step_4_SinkChannel;

@EnableBinding(Step_4_SinkChannel.class)
public class Step_4_Sink {

    @StreamListener("step-4")
    public void step4(CustomMessage msg) {
        System.out.println("Incoming Step-4 Msg: " + msg+"\n");
    }
}


