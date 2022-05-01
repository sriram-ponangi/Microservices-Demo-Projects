#REST API - OAuth2 Demo with SpringBoot:

## Three main components to implement OAuth2 With REST APIs:
### 1. Authorization Server 
### 2. Resource Server / Owner
### 3. Client Server


## Setting Up Authorization Server using KeyCloak:

### Step-1: Download and Start the Keycloak Server in Standalone mode. Then access the admin console.
![Step-1](keycloak-auth-server/1.PNG)
![plot](keycloak-auth-server/2.PNG)

### Step-2: Create a new Realm (Ex: SpringDemo)
![plot](keycloak-auth-server/3.PNG)
![plot](keycloak-auth-server/4.PNG)

### Step-3: Create a new Client (Ex: SpringBootDemo)
![plot](keycloak-auth-server/5.PNG)
![plot](keycloak-auth-server/6.PNG)
![plot](keycloak-auth-server/7.PNG)
![plot](keycloak-auth-server/8.PNG)

### Step-4: Access and note the important URLs for generating JWT token and validating it.
![plot](keycloak-auth-server/9.PNG)
![plot](keycloak-auth-server/10.PNG)

## Setting Up Resource Server using spring-boot-starter-oauth2-resource-server dependency:

### Step-5: Configure the spring boot resource-server application.
![plot](keycloak-auth-server/11.PNG)
![plot](keycloak-auth-server/12.PNG)
![plot](keycloak-auth-server/13.PNG)

### Step-6: Test the secured REST Endpoint by generating and using a Bearer token in Authorization header.
![plot](keycloak-auth-server/14.PNG)
![plot](keycloak-auth-server/15.PNG)
![plot](keycloak-auth-server/16.PNG)



## References:
- https://davidagood.com/oauth-client-credentials-auto-refresh-spring/
- https://davidagood.com/spring-security-disable-http-security/
