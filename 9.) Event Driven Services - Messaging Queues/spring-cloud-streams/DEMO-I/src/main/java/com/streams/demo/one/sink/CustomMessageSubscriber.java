package com.streams.demo.one.sink;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;

import com.streams.demo.one.models.CustomMessage;
import com.streams.demo.one.sink.channel.CustomMessageSink;

/*
 * Creating Subscriber using the custom channel called CustomMessageSink
 */
@EnableBinding(CustomMessageSink.class)
public class CustomMessageSubscriber {	
	
	/*
	 * Sending routing the message from the same channel
	 * to different methods based on a condition check...
	 */
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
    


    /*
     *Instead of using @StreamListener("commonMessageInChannel")
     *we are using @ServiceActivator from spring-integrations directly
     */    
    @ServiceActivator(inputChannel = "commonMessageInChannel")
    public void handleCommonMessage(CustomMessage msg) {
        System.out.println("Received: " + msg+"\n");
    }
}
