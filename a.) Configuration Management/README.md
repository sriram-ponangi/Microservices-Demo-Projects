# Configuration Management in Distributed Systems

---
## Introduction:
- Frequently used approaches to manage the configurations in a distributed system are :
	1. **Spring Cloud Consul**
	2. **Spring Cloud Zookeeper**
	3. **Spring Cloud Config Server**

- Approaches i & ii need integration with third party applications **Consul** and **Zookeeper**.

- Neither **Consul** nor **Zookeeper** are just configuration servers ie., they are also
  utilized to perform other common tasks like service-discovery, electing master node etc.
  
- Where as **Spring Cloud Config Server** by spring cloud team can be used as only a configuration server.


---
## Spring Cloud Config Server:

- It is used only to fetch configs and not to create/update any config files. To create/update we will have to directly do so in the respective config store.

- Spring-cloud-config project provides support for both **server** and **client**:
	- **Config-Server:**
		- It is a standalone web application
		- Provides REST based interface to access configuration files
		- The REST services support the following **response formats**:
			1. JSON (Default)
			2. YAML
			3. properties
		- The supported **config file stores** are:
			1. Git (Default)
			2. SVN
			3. File System
		- **Configurations Scope** can also be defined ie., in other words we can define where(Ex: environments:- Dev, Prod, etc.) a set of properties must be applied/used and this can be specified with the help of spring.profiles.active property.
				
		- In the config files store from which, the config-server will be serving the properties as REST API we must:
			- Add **.properties** or **.yml** files with the following naming convention:
				- **{application)-{profile}.yml** or **{application)-{profile}.properties**
					- {application} is the name of the application.
					- {profile} is the name of the environment the application should run in.
					- Ex: my-app-prod.yml
					
		- Details about the common REST Endpoints exposed by the Spring-cloud-config-server:
			- Common Parameters:
				- **{application}**
					- Refers to the application name
					- Maps to **spring.application.name** property on the client application
				- **{profile}**
					- Refers to the deployment environment name
					- Maps to **spring.profiles.active** property on the client application.
					- If there is no profile value in the config file name (ie., it has only application name) then we must specify profile value as **default**
				- **{label}**
					- This is a config-server feature that is used to refer a particular set of files.
					- For instance if the config files are stored in a git repository then label would refer to the branch name.
					- It is the only optional field and if nothing is given by default config files from the master branch will be served.
					- Maps to **spring.cloud.config.label** property on the client application.
			- API endpoints:
				- To get the configuration properties 
					- **GET: /{application}/{profile}/[/{label}]**
					- Ex: **/my-app/dev/release-1** 
					- Here, **my-app-dev.yml** file is searched for properties in the branch release-1.
				- To get the configuration properties when there is no profile
					- **GET: /{application}/default/[/{label}]**
					- Ex: **/my-app/default/** 
					- Here, **my-app.yml** file is searched for properties in the master branch.
				- To get the response in .yaml or .properties format irrespective of the format of the source config file
					- **GET: /{application}-{profile}.(yml | properties)**
					- Ex: **/my-app-dev.yml/** or **/my-app-dev.properties/** 
					- For this case the label is specified before
					- **GET: /{label}/{application}-{profile}.(yml | properties)**
					- Ex: **/release-1/my-app-dev.yml/** or **/release-1/my-app-dev.properties/** 
				
	- **Config-Client:** 
		- It provides out of the box support to be embedded into a Spring project by just adding a dependency and minimal configuration.
		- The config-client is responsible for bootstrapping and fetching the appropriate properties from the config-server.
			- Bootstrapping the application configuration is needed because before the spring application starts up it needs to resolve it's property sources. For instance, to inject values into the property placeholder ( ex: @Value ) spring actually needs to have values unless default values are provided. Therefore the config-client needs to fetch the properties from the config-server before the application starts.
			
		- Bootstrapping the application properties can be easily done by using a bootstrap.properties or bootstrap.yml file.
		
	- There are two ways to get the configurations from the config-server:
		1. [**Config First Approach:**](https://github.com/sriram-ponangi/Spring-Boot-Microservices-Demo-Projects/tree/master/a.\)%20Configuration%20Management "readme")
			- Get the properties directly from the Config-Server by looking at the location details specified in the bootstrap.yml file.
		2. [**Discovery First Approach:**](https://github.com/sriram-ponangi/Spring-Boot-Microservices-Demo-Projects/tree/master/a.\)%20Configuration%20Management/2.%20Discovery%20First/EurekaServer-ConfigServer "readme")
			- Get the properties from the Config-Server by requesting for it's location details from the discovery server(there by contacting discovery server first ie., before the config-server. Hence the name Discovery First)

	- Configuration management also helps in updating the properties in a running application dynamically without any down time:
		- Common example of where the dynamic update is being used is to change the logging levels.
		- This can be done by hitting the POST: /refresh endpoint provided by spring-boot-actuators dependency.		
		- Updating the application can be done either manually or in an automated way. In both cases we will still need to hit the POST: /refresh API.
			- Manually:
				- Notify applications to refresh configurations with POST: /refresh endpoint.
				- This needs to be done for every service and also for every individual instance of each service for the changes to be reflected.
					- If there are a lot of services, calling the /refresh on each would be very difficult
					- So an alternate approach for it would be, if each of the services would subscribe to an event then we can call use the **spring-cloud-bus** so that we can invoke a common endpoint like **/bus/refresh**. Then spring-cloud-bus would send out a message to all of the subscribers indicating them  to refresh their configuration.
					
					- Even with this approach we still have to manually call the refresh at least once.
					
			- Automatic:
				- To completely automate the process of refreshing the configurations we can use spring-cloud-config-monitor and spring-cloud-bus
				- Every time a commit is made in GIT the change set of the commit should be posted to a /monitor endpoint which then will internally determine which services need to update their configs.
				
		- Another point regarding the /refresh endpoint is that when it's called only the property values obtained from a class annotated with **@ConfigurationProperties** and the **logging levels** are updated by default. Where as the config property values obtained via the **@Bean or @Value** are not refreshed by default and for them we will have to apply the **@RefreshScope** explicitly. 
		
	- **Security in Spring-Cloud-Config-Server:**
		- ...
				
		
