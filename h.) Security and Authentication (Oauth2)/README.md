9 - Click to  view all URLs
#REST API - OAuth2 Demo with SpringBoot:

## Three main components to implement OAuth2 With REST APIs:
### 1. Authorization Server 
### 2. Resource Server / Owner
### 3. Client Server


## Setting Up Authorization Server using KeyCloak:

### Step-1: Download and Start the Keycloak Server in Standalone mode. Then access the admin console.
![plot](./keycloak-auth-server/1.png)
![plot](./keycloak-auth-server/2.png)

### Step-2: Create a new Realm (Ex: SpringDemo)
![plot](./keycloak-auth-server/3.png)
![plot](./keycloak-auth-server/4.png)

### Step-3: Create a new Client (Ex: SpringBootDemo)
![plot](./keycloak-auth-server/5.png)
![plot](./keycloak-auth-server/6.png)
![plot](./keycloak-auth-server/7.png)
![plot](./keycloak-auth-server/8.png)

### Step-4: Access and note the important URLs for generating JWT token and validating it.
![plot](./keycloak-auth-server/9.png)
![plot](./keycloak-auth-server/10.png)

## Setting Up Resource Server using spring-boot-starter-oauth2-resource-server dependency:

### Step-5: Configure the spring boot resource-server application.
![plot](./keycloak-auth-server/11.png)
![plot](./keycloak-auth-server/12.png)
![plot](./keycloak-auth-server/13.png)

### Step-6: Test the secured REST Endpoint by generating and using a Bearer token in Authorization header.
![plot](./keycloak-auth-server/14.png)
![plot](./keycloak-auth-server/15.png)
![plot](./keycloak-auth-server/16.png)

