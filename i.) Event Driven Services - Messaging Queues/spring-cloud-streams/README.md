# Event Driven Services - Messaging Queues (Kafka / RabbitMQ) 

---
## Contents :

- ## DEMO - I
  - ### Creating a Publisher and Subscriber using the **default channels(Source.INPUT and Sink.OUTPUT)** provided by spring-cloud-steam. 
    - Example: In DEMO-I look at:
      - *SimpleDefaultChannelPublisher.class*
      - *SimpleDefaultChannelSubscriber.class*
      - *application.yaml*
        ```yaml      
        spring:        
          cloud:
            stream:
              bindings:
                input:
                  destination: default_channel_messages
                output:
                  destination: default_channel_messages
              default:
                contentType: application/json
        .
        .
        .
        ```
  - ### Creating a Publisher and Subscriber using the custom channels
    - #### There are 3 types of channels that we can create which are 
      - **Sink :** (Subscriber channel) must be an interface with atleast 1 method having ```@Input(<channelName>)``` annotation
      - **Source :** Publisher channel) must be an interface with atleast 1 method having ```@Output(<channelName>)``` annotation
      - **Processor :** (Both Publisher & Subscriber) hence must atleast 2 methods one having ```@Input(<channelName>)``` and other ```@Output(<channelName>)```
      - **Note :** If a channel name is not given then the method name will be the channel name by default.
      - Channel and topics/exchanges are not the same. For instance if there is a publisher sending messages and a subscriber receiving a message from the topic called "TestTopic". Then there will be two channels(Ex: Sink.INPUT and Source.OUTPUT) both of which will be having destination as "TestTopic" which is usually represented in the properties files.
  - ### Customizing **InboundChannelAdapter** to automatically publish a message every 2 seconds instead of the default 1 second
  - ### Dispatch messages to multiple StreamListeners methods - Based on header values invoke different methods
  - ### Use **ServiceActivator** from spring-integration on receiver instead of **StreamListener** from spring-cloud-stream
    - Example: In DEMO-I look at:
      - *interface CustomMessageSource and CustomMessagePublisher.class*
      - *interface CustomMessageSink and CustomMessageSubscriber.class*
        ```yaml
        spring:
          cloud:
            stream:
              bindings:
                input:
                  destination: default_channel_messages
                output:
                  destination: default_channel_messages              
                  
                importantMessageOutChannel:
                  destination: important_message_channel
                  contentType: application/json
                importantMessageInChannel:
                  destination: important_message_channel
                  contentType: application/json

                commonMessageOutChannel:
                  destination: common_message_channel
                commonMessageInChannel:
                  destination: common_message_channel
          .
          .
          .              
        ```

- ## DEMO - II
  - ### Create a **Processor** stream app
    - Example: In DEMO-II refer :
      - *Step_1_SourceChannel.class* and *Step_1_Source.class*
      - *Step_2_3_ProcessorChannel.class* and *Step_2_3_Processor.class*
      - *Step_4_SinkChannel.class* and *Step_4_Sink.class*
      - *application.yaml*
        ```yaml      
        spring:
          rabbitmq:
            host: 192.168.99.101
            port: 5672
            username: guest
            password: guest
          cloud:
            stream:
                step-1: #Sending a message to topic-a using Source(Output)
                  destination: topic-a
                step-2: #Reading a message from topic-a using Processor(Input)
                  destination: topic-a
                step-3: #Sendding a message to topic-b using Processor(Output)
                  destination: topic-b
                step-4: #Reading a message from topic-b using Sink(Input)
                  destination: topic-b

              default:
                contentType: application/json
        .
        .
        .
        ```  
  - ### Add a new REST endpoint which pushes new a message into the queue every time it's hit instead of continuously sending after every x no. of seconds.
     - Example: In DEMO-II refer :
       - *SendMessageController.class* has /message (POST) which add a message into the queue.
       - *CustomChannels.class*, *CustomChannelProcessor.class* and *CustomChannelSink.class*

  - ### Create a consumer group
     - ### Start multiple instances of the same app and observe if only one of the instances in that group are processing/getting the message.
     - ### Also, stop all instance of the group but keep sending messages into the queue and then start one or few of the instances of the app and check if they are processing the messages sent during their down time.
  - ### Using kafka for some queues and rabbitMQ for others in the same project.
    - Example: In DEMO-II refer *application.yaml*:
    ```yaml      
        server:
          port: 0

        spring:
          rabbitmq:
            host: 192.168.99.101
            port: 5672
            username: guest
            password: guest
          cloud:
            stream:
              kafka:
                binder:
                  brokers:
                  - localhost:9092
              bindings:
                out:
                  destination: message_out
                  group: api
                  binder: kafka
                input:
                  destination: message_out
                  group: api
                  binder: kafka
                output:
                  destination: message_in
                  group: api
                  binder: kafka
                in:
                  destination: message_in
                  group: api
                  binder: kafka

                step-1: #Sending a message to topic-a using Source(Output)
                  destination: topic-a
                step-2: #Reading a message from topic-a using Processor(Input)
                  destination: topic-a
                step-3: #Sendding a message to topic-b using Processor(Output)
                  destination: topic-b
                step-4: #Reading a message from topic-b using Sink(Input)
                  destination: topic-b

              default:
                contentType: application/json
                binder: rabbit
        ```  