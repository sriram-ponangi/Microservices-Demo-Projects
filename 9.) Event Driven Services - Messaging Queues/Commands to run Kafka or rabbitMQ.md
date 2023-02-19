## Kafka for window:
---

```bash
# Starting Zookeeper Service:
# ===========================
> .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

# Starting Kafka Sever:
# ===========================
> .\bin\windows\kafka-server-start.bat .\config\server.properties

# Create Kafka Topic:
# ===========================
> .\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic streams-demo-topic

# Start Kafka Producer in CMD:
# ============================
> .\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic  streams-demo-topic

# Start Kafka Consumer in CMD:
# ============================
> .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic message_out  --from-beginning
```


## RabbitMQ docker image:
---
```bash
$ docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 8080:15672 rabbitmq:management-alpine
```