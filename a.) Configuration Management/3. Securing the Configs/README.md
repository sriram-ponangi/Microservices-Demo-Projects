# Securing the Configurations with Spring-Cloud-Config-Server:
---

## Encryption of Secrets:
- Useful to store the secrets/passwords in an encrypted format while they are in a config store like git repository.
- These encrypted secrets are then served to the client. The decryption of secrets can be done either at:
	- Server side and then plain text values can be sent to client or,
	- Client side where the server simply forwards the secrets as it is and the client decrypts them directly.
- Pre-requisites:
	- Ensure the **Java Cryptography Extension (JCE)** Unlimited Strength Jurisdiction Policy Files used in the JRE or use OpenJDk new versions where it comes by default. 	
	
	#### 1. Symmetric Encryption:
		- Using the same key to Encrypt and Decrypt the secrets.
		- First create a bootstrap.properties|yml file and add the following property:
		```
		encrypt:
			key: SomeRandomCharactersForSymmetricEncryption
		```	
	
	#### 2. Asymmetric Encryption:
		- Using the Public-Private key Pair to Encrypt and Decrypt the secrets. 
		- First generate a asymmetric key:
			- Example Command to generate an Asymemtric KeyStore that is, a .jks file using JDKs command line utility the **"Keytool"** is:
			```
				keytool -genkeypair -alias mytestkey -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" 
				-keypass MyPassword -keystore configServerKeystore.jks -storepass MyPassword			
			```
			
		- Then create a bootstrap.properties|yml file and add the following property which must match what was given while running the Keytool command shown above:
		```
		encrypt:
			keyStore:
				location: classpath:asymetricKey/configServerKeystore.jks
				password: MyPassword
				alias: mytestkey
				secret: MyPassword
		```
		- Here the configServerKeystore.jks file is stored inside the *src/main/resourse/asymetricKey/* directory
		- **Note:**
			- We can use the endpoint POST http://localhost:8888/encrypt and pass a secret in plain text to get the encrypted value similarly we can also decrypt the secret with another endpoint called POST http://localhost:8888/decrypt

	#### 3. Then we can store the secrets in the application configuration files as, example:
	
		- **Without Encryption**:
		```
		secrets:
			password: My_Password
		```
		- **With Encryption**: *My_Password* is encrypted and the new encrypted string must be prefixed with **{cipher}**
		```
		secrets:
			password: "{cipher}c8da3ba922ac363dab42cb17b95c435aa92bba702551ef9b036963c0c856143aa5a6d8190ec3480692c857998dfc658f"
		```
		
	#### 4. Client Side Decryption: 
		- Instead of the config-server decrypting the secrets and giving it to the client we can also directly send the encrypted secret and let the client decrypt by providing the key information to the client.
		- To disable the encryption add this configuration in config-server:
		```		
		spring:
		    cloud:
			  config:
				server:
				  encrypt:
					enabled: false
		```
		- Then provide the Key information to decrypt the secret in client configuration file:
			- **For Symmetric Key:**
			```
			encrypt:
				key: SomeRandomCharactersForSymmetricEncryption
			```	
			- **For Asymmetric Key:**
			```
			encrypt:
				keyStore:
					location: classpath:asymetricKey/configServerKeystore.jks
					password: MyPassword
					alias: mytestkey
					secret: MyPassword
			```
---
## Authentication of Services (End-Points):
- **Config-Server:**
	- Add the dependency **spring-boot-starter-security** in the config-server
	- Then in the application.properties|yml set the username and password to authenticate the execution of REST endpoints in the config-server:
	```	
	spring:	 
		security:    
		   user:
			 name: admin
			 password: admin
	```
	- **NOTE:**
		- While trying to run any of the endpoints from config-server we must set username and password in **Autherization: Basic Auth**
- **Config-Client:**
	- In the application.properties|yml add the username and password to access the REST endpoints in the config-server from config-client:
	```
	spring:	
		cloud:
		  config:
			 uri: http://localhost:8888
			 username: admin
			 password: admin
	```





## NOTE:
- In Spring Boot 2 when the security is used the CSRF is enabled by default and we cannot disable CSRF using properties it forces us to implement a java security config bean. Example: com.example.config.server.configs.WebSecurityConfig.java

- Demo Application Endpoints to Test:
	- **Config-Server:**
		- Set username = admin and password = admin in **Autherization: Basic Auth** 
			- GET http://localhost:8888/symmetric-encryption-sample/prod
			- GET http://localhost:8888/symmetric-encryption-sample/dev
			- GET http://localhost:8888/asymmetric-encryption-sample/prod
			- GET http://localhost:8888/asymmetric-encryption-sample/dev
			- POST http://localhost:8888/encrypt Body: SecretInPlainText
			- POST http://localhost:8888/decrypt Body: EncryptedSecretAsText
	- **Config-Client:**
		- http://localhost:8080
	
	
	