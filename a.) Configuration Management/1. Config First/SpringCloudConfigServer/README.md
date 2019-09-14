# SPRING CLOUD CONFIG SERVER

---
## CONFIG-SERVER:
### 1.) Add Dependencies:
 - spring-cloud-config-server
 - spring-boot-starter-actuator(Optional)

### 2.) Add @EnableConfigServer on the corresponding Application.java class

### 3.) Configuring the location of store/directory having the configuration files:

 - For **Native Directory / Folder from local machine** set value for _spring.profiles.active_ as _native_
 
   - When spring.profiles.active=native, by Default the config-server looks for config files in _classpath:resources/config/_   
    - **EXAMPLE-1:**
     ```yaml
        server:
         port: 8888  
        spring:
         profiles:
          active: native
      ```
   - To give Custom Directory Path set value for _spring.cloud.config.server.search-locations_ as _file:PATH_
    - **EXAMPLE-2:**
     ```yaml
        server:
         port: 8888
        spring:
         profiles:
          active: native
         cloud:
          config:
           server:
            native:
             search-locations: file:C:\Users\SRIRAM\Desktop\configs-store
     ``` 
					  
 - **GIT Repository**
   - To get configuration files from a git repository 
    - **EXAMPLE-3:**   
        ```yaml
        spring:
         cloud:
          config: 
           server:
            git:
             uri: https://github.com/sriram5795/Microservice-Demo-Projects-Configs.git
             search-paths: 1. Spring Cloud Config Server/1.1 Config Server-Config First Approach/remote-properties
        ``` 
     - **search-paths** specifies the directory path inside the repository to find config files
     
   - To get configuration files from **_private_** remote git repository set values for **_username and password_**
    - **EXAMPLE-4:**
        ```yaml
         spring:
         cloud:
          config: 
           server:
            git:
             uri: https://github.com/sriram5795/Microservice-Demo-Projects-Configs.git
             search-paths: 1. Spring Cloud Config Server/1.1 Config Server-Config First Approach/remote-properties
             username: xyz@gmail.com
             password: xzy     
        ```
	
---	
## CONFIG-CLIENT:
### 1.) Add Dependencies:
 - spring-cloud-starter-config
 - spring-boot-starter-actuator(Optional)
 - spring-boot-starter-web
### 2.) Create bootstrap.yml in /resources
 - Add the following properties:
   - **_spring.profiles.active_** specifies the deployment environment
   - **_spring.application.name_** specifies the microservice name
   - **_spring.cloud.config.uri_** specifies the config-server url
   - **_spring.cloud.config.label_** [**OPTIONAL**]specifies the brach name when the config store refered by config server is git repo and not native
     ```yaml
     spring:
      profiles:
       active: uat
      application:
       name: config-client
      cloud:
       config:
        uri: http://localhost:8888
        label: config-server-first-approach

     management:
      endpoints:
       web:
        exposure:
         include: '*'

     info:
      name: ${spring.application.name}
      profile: ${spring.profiles.active}
     ```
   - **_management.endpoints.web.exposure.include_** exposes actuator endpoints and * means expose all
   - **_info_**: Adding fields in the response of actuator/info endpoint
   
#### NOTE:
  - Lable specifies the branch name where the config-server should look for the directories(ie.,search-paths) and the config files.
  - By default **master** branch is taken we can change that by setting value for **spring.cloud.config.label** in the config-server **client** microservice.
  - Example:
      ```yaml
      spring:
       cloud:
        config:
         label: branch-name
      ```

  - Test With: http://localhost:8888/FileName/profiles/label     
    - Example: http://localhost:8888/microservice-one/uat/branch-name  
    
    
---    
## REFRESH CONFIG PROPERTIES DYNAMICALLY:    
### 1.) The configuration changes made in yml or properties file can be updated in the microservice without restarting them by:
- Add the actuators dependency in the client of config-server.
- By hitting the POST service endpoint **/actuator/refresh** in the microservice for which we want to update the configs.
- Sample Test Case:
    - Use the configuration yml file in my **Springboot-Demo-Projects-Configs-Store** repository.
    - Start the service with spring.profiles.active=uat
    - Run the service.
    - Do a GET call with /mail endpoint
      ``` 
      Expected Output:
      Mail [subject=UAT Mail Subject, to=to@mail.come, from=from@mail.com]
      ```
    - Set the property **spring.cloud.config.label= config-server-first-approach** to change the branch from where the new config file
      can be used.
    - Then do a POST call with /actuator/refresh/ endpoint
       ``` 
       Expected Output: The response has the list of updated properties. Here, the git branch is changed and
	   the property files in them differ in the following properties:
	  [
	   "config.client.version",
	   "message.two",
	   "mails.from",
	   "spring.cloud.config.label",
	   "mails.subject",
	   "message.one",
	   "mails.to"
	  ]       
       ```
    - Do a GET call again with /mail endpoint
      ``` 
      Expected Output:
      Mail [subject=default subject, to=default@mail.com, from=default@mail.com]
      ```
  
