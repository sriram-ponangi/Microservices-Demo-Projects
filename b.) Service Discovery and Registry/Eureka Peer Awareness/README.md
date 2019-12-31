# Service Discovery and Registry with Eureka with Peer Awareness.

- When running the repicas on the same machine(ie., localhost) while devlopment or for demo purposes we need to add new DNS mapings(loop back address) in the built-in DNS file called hosts.

- File Path:
	- Windows: **C:\Windows\system32\drivers\etc\**
	- Linux: **/etc/hosts**

- Add the following content in the hosts file:
	```
	127.0.0.1       eureka1
	127.0.0.1       eureka2
	127.0.0.1       eureka3
	```

## NOTE:-
Once a microservice is up and registers itself with eureka as client it also get a copy of the details of all the other registered serives so that it can directly do client side load balancing instead of going to the eureka server every time for details of the microservice it wants to talk to. In other words even if all the eureka instances go down with all the other microservices that we want to talk to still being up we will still be able to continue with the successfull execution of the request.



