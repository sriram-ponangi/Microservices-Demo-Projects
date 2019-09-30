package com.example.stream.demo.sink;

import com.example.stream.demo.configs.SinkChannel;
import com.example.stream.demo.models.CustomMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(SinkChannel.class)
public class StepFourSink {

    @StreamListener("step-4")
    public void step4(CustomMessage msg) {
        System.out.println("Incoming Step-4 Msg: " + msg+"\n");
    }
}


