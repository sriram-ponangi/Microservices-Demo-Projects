package com.stream.demo.sinks;

import com.stream.demo.models.CustomMessage;
import com.stream.demo.sinks.configs.CustomMessageSink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;

@EnableBinding(CustomMessageSink.class)
public class CustomMessageSubscriber {

    @StreamListener(target = "importantMessageInChannel", condition = "headers['priority'] < 30")
    public void handleHighPriorityImportantMessage(CustomMessage msg) {
        System.out.println("\n****************************************");
        System.out.println("VERY HIGH PRIORITY MESSAGE Received: " + msg);
        System.out.println("****************************************\n");
    }

    @StreamListener(target = "importantMessageInChannel", condition = "headers['priority'] >= 30")
    public void handleLoePriorityImportantMessage(CustomMessage msg) {
        System.out.println("\n-----------------------------------------");
        System.out.println("low priority message Received: " + msg);
        System.out.println("-------------------------------------------\n");
    }


//    @StreamListener("commonMessageInChannel")
    @ServiceActivator(inputChannel = "commonMessageInChannel")
    public void handleCommonMessage(CustomMessage msg) {
        System.out.println("\nReceived:\n " + msg+"\n");
    }
}
