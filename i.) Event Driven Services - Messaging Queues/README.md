# Event Driven Services - Messaging Queues (Kafka / RabbitMQ) 

---
## Introduction:
- Microservices can communicate with each other using messages/events.
- Some of the benefits of connecting microservices through messaging are:
  - It encourages **loose coupling** among them (ie., avoiding linkages between their endpoints, business logic, etc.) and this helps in evolving them independently.
  - The services can be **scaled** easily
  - **Reliablity** of communication among the service increases.
- **NOTE :** It is advisible to do the processing / business logic in an asynchronous and non-blocking fashion while using messaging to take full advantage of it.
- We will be using the following for creating a demo on event driven microservices:
  1. Framework: **Spring-Cloud-Streams**  
  2. Messaging Queues: **Kafka or RabbitMQ**

---
## Spring-Integration:

- **Spring-cloud-stream** is created on top of Spring-integrartion
- Some of the important concepts from Spring-integrartion:
  - **```Message<T>```** object has a payload and a few headers that provide some meta-data regarding the payload.
  - **```MessageChannel<T>```** is like a java Queue object. The ```Message<T>``` objects flow through an instance of MessageChannel.
  - **Channel adapters** are used for connecting to the messaging sources( ie., a single sender or a receiver to a Message Channel).
    - **Inbound adapter** takes a message from the outside world and turns that into ```Message<T>``` object
    - **Outbound adapter** also does the same thing but in reverse ie., it takes a ```Message<T>``` object and sends it as a message to some downstream system.
  - **```ServiceActivator```** can pull the ```MessageChannel<T>``` for looking at the messages and when a message appears in one of these channels, it invokes appropriate method attached to that bean.
---
## Spring-Cloud-Streams:

  - The main advantage of using spring-cloud-stream over spring-integration is that we do not have to add any Message Brocker specific code as everything is decoupled.

  - Important concepts in Spring-Cloud-Streams:

    - ### **Channels :**
      - Microservices/Apps communicate with each other using the channels.

    - ### **Binders :**
      - It helps us connect us to the enpoints of the external middleware (Ex: MessageBroker, Queue, Topics/Exchanges, etc.)      
      - We have to add the binder dependency into the classpath and spring-cloud-stream automatically detects/connects to it.
      - We can connect to multiple brokers of the same type.
        - For example connecting to multiple RabbitMQ or kafka environments.
      - Using different binders within the same code is also possibe.
        - For example using kafka for some messages and RabbitMQ for others.
      - We can also write our own custom binders.      
      - Middleware(Messaging Queues Ex: Kafka) details are abstracted using Binders. Therefore it is middle-ware neutral ie., in other words we donot need to know/implement code specific to the API of the middleware used.
      - Because of the binders we donot hardcode any relationship to the messaging queues and because of this they can be chaged at runtime with jsut some minor changes in the **configuration properties**.
      - ```@EnableBinding``` is the annotation used here, which is a part of the binders abstraction. These are the types which we can use:
        - **Source:** 
          - It is like a producer/publisher.
          - Used to send a message into the queue/topic.
          - Hence has only an Outbound Channel.
          - Example: ```@EnableBinding(Source.class)```
        - **Sink :**
          - It is like a consumer/subscriber.
          - Used to consume a message from a queue/topic.
          - Hence has only an Inbound Channel.
          - Example: ```@EnableBinding(Sink.class)``` 
        - **Processor :**
          - It is used to do both sending and receiving a message. That is used to consume a message from a queue/topic then, do some operations/processing over it and publish the updated message to a new queue/topic.
          -  Hence has both Inbound and Outbound Channels.
          - Example: ```@EnableBinding(Processor.class)```
          - It uses ```@SendTo``` to set out destination of the message returned by the method.
          - It is also possible to use different brokers for the inbound/outbound channels in the same Processor (ex: receive from kafka and publish to rabbitmq).
          - **It is useful when we need to chain events**

    - ### **@StreamListener :**
      - It is a unique feature specific to spring-cloud-streams(ie., not from spring-integration).
      -  It is an easy way for processing the data coming from a message queue that is it's a **handler for inbound messages**.
      - It also does some **automatic content type conversion** ie., by looking into the content-type header it parses the message to a particular POJO.
      - We can also **dispatch the incoming message to multiple methods**  based on conditional checks.
        - For instance  we can pull message from a particular queue/topic and check if a property in the message(Headers or Body) matches something then invoke some operation.
      - Look at ***Example: For Receiving a Message*** --> (2) down below.
      - ### Example-1 : **For Sending a Message**        
          ```java 
          @EnableBinding(Source.class) //---------------------> (1)
          public class MyMessageSource {
            // Auto Push every 1 second...
            @InboundChannelAdapter(value=Source.OUTPUT) //----> (2)
            public String sendMessage(){
              return "Polling message....";
            }
          }
          ```           
          - (1) --> Makes the class as a Stream app:
              - **Source, Sink, Processor** -> ( Built in basic interfaces )
          - (2) --> **InboundChannelAdapter** is one way to emit data using spring-integration.
            - By default the method annotated with it will output the information every second.
            - Here, we are returning a String but usually we return a **MessageSource** object where we can have payload, headers and type management.        
          ```yaml
            spring:
            cloud:
              stream:
              bindings:
                output: # --------------------> (3) 
                destination: mymessages # ----> (4)
            rabbitmq:
              host: localhost
              port: 5432
              username: guest
              password: guest
          ``` 
          - (3) --> **output** maps to **Source.OUTPUT** in ---> (2)
          - (4) --> referes to the actual **queue/topic name** in middleware is *mymessages*
            - Then, it creates and/or connects to the queue called *mymessages*
            - By default it would use the name of the channel
          - **Some customizations we can do for sending/publishing a message in Example-1:**
            - **InboundChannelAdapter :** (As seen in the Example Above...)
              - We can customize it so that instead of sending/publishing a message every second we can change the frequency.
              - We can setup a **Batching** ie., we can set how many messages should be combined  before sending.
              - Return a ```MessageSource<T>``` instead of a String.
            - **We can create custom interfaces for channels** instead of just using Sink.**INPUT** / Source.**OUTPUT**.
            - **Pushing messages by injecting bound interface or channel directly** that is, instead of publishing a message at a certain interval we could send it based on a REST API call.
      - ### Example-2 : **For Receiving a Message**       
        ```java 
        @EnableBinding(Sink.class) //-------------> (1)
        public class MyMessageReceiver {
          // Auto Push every 1 second...
          @StreamListener(value=Sink.INPUT) //----> (2)
          public void printMessage(String msg){
            System.out.println(msg);
          }
        }
        ```             
        - (1) --> Makes the class as a Stream app:
        - (2) --> Makes it listen to the incoming messages to the INPUT configured topic(ie., mymessages here).
        ```yaml
            spring:
            cloud:
              stream:
              bindings:
                output: # --------------------> (3) 
                destination: mymessages # ----> (4)
            rabbitmq:
              host: localhost
              port: 5432
              username: guest
              password: guest
          ```
          - (3) --> **input** maps to **Sink.INPUT** in ---> (2)
            - The annotation **@StreamListener** is used in the receiver here.
          - (4) --> referes to the actual **queue/topic name** in middleware is *mymessages*
          - **Some customizations we can do for receiving a message in Example-2 :**
            - Use Spring-Integrations implementation **ServiceActivator** directly instead of **@StreamListener**.
            - We can **set conditions on @StreamListener** to  dispatch the message to different methods ie., routing messages based on headers and payload.
       
    - ### **Consumer Groups :** 
      - Instead of having **point-to-point** connectivity points, we can reduce coupling by using **"shared topics"** where we can publish a messgae and anyone interested can subscribe to that topic to get a copy of the message.
      - **But if we have multiple instances of the same microservice subscribed to the topic we can use the *consumer groups* to put them in a competing consumers relationship where only one of the instances in the group will get the message.**
      - Wether Consumer Groups is an inbuilt concept in messaging queues like kafka or not like rabbitmq, the abstraction provided by the spring-cloud-streams framework handles it.
      - If we donot specify a consumer group then by default the app gets assigned to an anonymous independent single member group which causes each instance of the app to get a seperate copy of the message.
      - By default **Consumer Groups are durable** but when a consumer group is not specified then the subscriptions becomes non-durable.
        - In other words, if we stop a consumer when a consumer group is not specified then the subscription of that consumer also goes away and messages that keep coming into that queue after the subscription is gone get never picked up by that consumer again even if the consumer restarts immediately.
        - Where as when a consumer group is specified even if all the consumers in the group go down the messages continue to go into the target queue and when one of the consumers in the group come back up the messages which came in the down-time are still there which the consumer will start reading.
      - To use consumer groups we set this property in configs:
        ```yaml
        spring:
         cloud:
          stream:
           bindings:
            <channelName>:
              group: <groupName>
        ``` 

    - ### **Stateful processing using Partioning :**
      - If we want to split/partition the received data(messages) on some common field in the data(ex: source of the message, status of an order, etc.) and send them to some specific instances. Basically to achieve stateful processing of the message irrespective of the messaging broker we can use spring-cloud-streams.
---
## Demo Content :
- Using the Default channels (INPUT and OUTPUT) create
  - Simple String message
  - Sender: Source.class
  - Receiver: Sink.class
- Create **custom interfaces and channels** for Sink, Source and Processor
-  **Create a custom interface for sender** instead of using Source.class
-  Use **InboundChannelAdapter** to automatically publish a message every 2 seconds instead of the default 1 second.
-  Use **ServiceActivator** on receiver instead of **StreamListener**
-  Create a **Processor** stream app
-  Dispatch messages to multiple StreamListeners methods
   - Based on header values invoke different methods
- Add a new REST endpoint which pushes new a message into the queue every time it's hit instead of continuously sending after every x no. of seconds.
- Create a consumer group
   - Start multiple instances of the same app and observe if only one of the instances in that group are processing/getting the message.
   - Also, stop all instance of the group but keep sending messages into the queue and then start one or few of the instances of the app and check if they are processing the messages sent during their down time.
- Using **kafka** for some queues and **rabbitMQ** for others **in the same project.**

---
## REFERENCES :
- ### [Doccumentation](https://docs.spring.io/spring-cloud-stream/docs/Brooklyn.SR3/reference/htmlsingle/)
