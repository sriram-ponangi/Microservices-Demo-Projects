# Generate a client and server RSA 4096 private key and self-signed key/cert pair:

### Generating Client KeyStore:
```shell
keytool -genkeypair -alias client -keyalg RSA -keysize 4096 -ext SAN=dns:localhost,ip:127.0.0.1 -dname "CN=*,OU=Client,O=MyOrg Ltd,L=Toronto,S=ON,C=CA" -keypass changeit -storetype PKCS12 -keystore client-keystore.p12 -storepass changeit
```

### Generating Server KeyStore:
```shell
keytool -genkeypair -alias server -keyalg RSA -keysize 4096 -ext SAN=dns:localhost,ip:127.0.0.1 -dname "CN=*,OU=Server,O=MyOrg Ltd,L=Toronto,S=ON,C=CA" -keypass changeit -storetype PKCS12 -keystore server-keystore.p12 -storepass changeit
```



# Export public certificates from the client and server key-pair file:

### Generating Client Certificate:
```shell
keytool -exportcert -alias client -file client-public.cer -keystore client-keystore.p12 -storepass changeit
```

### Generating Server Certificate:
```shell
keytool -exportcert -alias server -file server-public.cer -keystore server-keystore.p12 -storepass changeit
```



# Import the client and server public certificates into each others truststores:

### Generating Client TrustStore:
```shell
keytool -importcert -keystore client-truststore.p12 -storetype PKCS12 -alias server-public-cert -file server-public.cer -storepass changeit -noprompt
```

### Generating Server TrustStore:
```shell
keytool -importcert -keystore server-truststore.p12 -storetype PKCS12 -alias client-public-cert -file client-public.cer -storepass changeit -noprompt
```

# Verify KeyStore/TrustStore

### Verifying Client KeyStore:
```shell
keytool -list -v -keystore client-keystore.p12 -storepass changeit
```
### Verifying Client TrustStore:
```shell
keytool -list -v -keystore client-truststore.p12 -storepass changeit
```

### Verifying Server KeyStore:
```shell
keytool -list -v -keystore server-keystore.p12 -storepass changeit
```

### Verifying Server TrustStore:
```shell
keytool -list -v -keystore server-truststore.p12 -storepass changeit
```

---

# Client Application Configs:

> application.yml
```yaml
server:
	port: 8441
	ssl:
		enabled: true
		key-store-type: PKCS12
		key-store: "classpath:keystores/client-keystore.p12"
		key-store-password: changeit
		key-alias: client
		key-password: changeit
```


> JVM Arguments For REST Client
```shell
-Djavax.net.debug=all
-Djavax.net.ssl.trustStore=/absolute/path/to/keystores/client-truststore.p12
-Djavax.net.ssl.trustStorePassword=changeit
-Djavax.net.ssl.trustStoreType=PKCS12
-Djavax.protocol=TLSv1.3
```

# Server Application Configs:

> application.yml
```yaml
server:
	port: 8443
	ssl:
		enabled: true
		key-store-type: PKCS12
		key-store: "classpath:keystores/server-keystore.p12"
		key-store-password: changeit
		key-alias: client
		key-password: changeit
```


> JVM Arguments For REST Client
```shell
-Djavax.net.debug=all
-Djavax.net.ssl.trustStore=/absolute/path/to/keystores/server-truststore.p12
-Djavax.net.ssl.trustStorePassword=changeit
-Djavax.net.ssl.trustStoreType=PKCS12
-Djavax.protocol=TLSv1.3
```
