# 9. Event Driven Services - Messaging Queues (Kafka / RabbitMQ) 

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
      - It helps us connect us to the enpoints of the external middleware (Ex: MessageBroker, Queue, Topics, etc.)
      - We have to add the binder dependency into the classpath and spring-cloud-stream automatically detects/connects to it.
      - We can connect to multiple brokers of the same type.
        - For example connecting to multiple RabbitMQ or kafka environments.
      - Using different binders within the same code is also possibe.
        - For example using kafka for some messages and RabbitMQ for others.
      - We can also write our own custom binders.      
      - Middleware(Messaging Queues Ex: Kafka) details are abstracted using Binders. Therefore it is middle-ware neutral ie., in other words we donot need to know/implement code specific to the API of the middleware used.
      - Because of the binders we donot hardcode any relationship to the messaging queues and because of this they can be chaged at runtime with jsut some minor changes in the **configuration properties**.
      - ```@EnableBinding``` is the annotation used here, which is a part of the binders abstraction.

    - ### **@StreamListener :**
      -  It is a unique feature specific to spring-cloud-streams(ie., not from spring-integration).
      -  It is an easy way for processing the data coming from a message queue that is it's a **handler for inbound messages**.
      - It also does some **automatic content type conversion** ie., by looking into the content-type header it parses the message to a particular POJO.
      - We can also **dispatch the incoming message to multiple methods**  based on conditional checks.
        - For instance  we can pull message from a particular queue/topic and check if a property in the message(Headers or Body) matches something then invoke some operation.
      - Look at ***Example: For Receiving a Message*** --> (2) down below.
         
      - ### Example: **For Sending a Message**       
         
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
      
      - ### Example: **For Receiving a Message**     
        
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
       
    - ### **Consumer Groups :** 
      - Instead of having **point-to-point** connectivity points, we can reduce coupling by using **"shared topics"** where we can publish a messgae and anyone interested can subscribe to that topic to get a copy of the message.
      - But if we have multiple instances of the same microservice subscribed to the topic we can use the **consumer groups** to put them in a competing consumers relationship where only one of the instances will get the message.
      - Wether Consumer Groups is an inbuilt concept in messaging queues like kafka or not like rabbitmq, the abstraction provided by the spring-cloud-streams framework handles it.
    - ### **Stateful processing using Partioning :**
      - If we want to split/partition the received data(messages) on some common field in the data(ex: source of the message, status of an order, etc.) and send them to some specific instances. Basically to achieve stateful processing of the message irrespective of the messaging broker we can use spring-cloud-streams.
    - 

## Contents:

- Source, Sink and Process
	- Creating Topics/Exchanges
	- Polling an event after a particular time interval
	- Generating Event using a REST API	
- Content Types
- Configure Groups

