# SPRING CLOUD CONFIG SERVER - With Eureka First Approach

---
## EUREKA-SERVER:

### 1.) Add Dependencies:
 - spring-cloud-starter-netflix-eureka-server
### 2.) Add @EnableEurekaServer on the corresponding Application.java class
### 3.) Add the following properties in application.properties file:  
```properties
        server.port=8761        
        #Do not register eureka-server to itself
        eureka.client.register-with-eureka=false
        eureka.client.fetch-registry=false     
        
```
     

---
## CONFIG-SERVER:

### 1.) Add Dependencies:
 - spring-cloud-config-server
 - spring-cloud-starter-netflix-eureka-client
### 2.) Add @EnableConfigServer and @EnableDiscoveryClient on the corresponding Application.java class
### 3.) Add the following aditional properties in _application.yml_ file apart from the ones mentioned in [SpringCloudConfigServer](https://github.com/sriram5795/Springboot-Demo-Projects/tree/master/1.%20Configuration%20Management/1.%20Config%20First/SpringCloudConfigServer#3-configuring-the-location-of-storedirectory-having-the-configuration-files "readme"): 
```yaml
        #Details for registering in eureka          
        eureka:
          instance:
            instance-id: ${spring.application.name}:${random.int}
          client:
            serviceUrl:
              defaultZone: ${EUREKA_URI:http://localhost:8761/eureka}   
``` 


---
## CONFIG-CLIENT:

### 1.) Add Dependencies:
 - spring-cloud-starter-config
 - spring-cloud-starter-netflix-eureka-client
### 2.) Add @EnableDiscoveryClient on the corresponding Application.java class
### 3.) Add the following aditional properties in _bootstrap.yml_ file apart from the ones mentioned in SpringCloudConfigServer: 
```yaml
        spring:
         profiles:
          active: uat
         application:
          name: config-client
         cloud:
          config:
           discovery:
            enabled: true # Get Configs from config server registered in discovery server
            service-id: config-server  # Name of the config server registered in eureka   
           uri: http://localhost:8888
          
        #Details for registering in eureka          
        eureka:
          instance:
            instance-id: ${spring.application.name}:${random.int}
          client:
            serviceUrl:
              defaultZone: ${EUREKA_URI:http://localhost:8761/eureka}   
              
``` 
 


