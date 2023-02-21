Generate Private-Public Key pair:
> openssl genrsa -out rootCA.key 2048


Extract Public Key from Key pair:
> openssl rsa -in rootCA.key -pubout -out rootCA.public.key

Creating CSR
> openssl req -new -key rootCA.key -out rootCA.csr


Verify CSR
> openssl req -text -in rootCA.csr -noout -verify

Create a self signed certificate
> openssl x509 -in rootCA.csr -out rootCA.crt -req -signkey rootCA.key -days 365



--------------------------------------------------------------------------------


Generate Private-Public Key pair:
> openssl genrsa -des3 -out rootCA.key 2048



--------------------------------------------------------------------------------



> openssl pkcs12 -export -in rootCA.crt -inkey rootCA.key -name rootCA -out test_keystore.p12
> password: changeit




keytool -import -alias rootCA -file rootCA.crt -keystore test_truststore2.p12
keytool -import -alias rootCA -file rootCA.crt -storetype PKCS12 -keystore test_truststore3.p12






























=================================
CLIENT_KEYSTORE_DIR=../client/src/main/resources
SERVER_KEYSTORE_DIR=../server/src/main/resources
CLIENT_KEYSTORE=$CLIENT_KEYSTORE_DIR/client-nonprod.jks
SERVER_KEYSTORE=$SERVER_KEYSTORE_DIR/server-nonprod.jks
JAVA_CA_CERTS=$JAVA_HOME/jre/lib/security/cacerts

# Generate a client and server RSA 2048 key pair
keytool -genkeypair -alias client -keyalg RSA -keysize 2048 -dname "CN=Client,OU=Client,O=PlumStep,L=San Francisco,S=CA,C=U" -keypass changeit -storetype PKCS12 -keystore client-nonprod.p12 -storepass changeit

keytool -genkeypair -alias server -keyalg RSA -keysize 2048 -dname "CN=Server,OU=Server,O=PlumStep,L=San Francisco,S=CA,C=U" -keypass changeit -storetype PKCS12 -keystore server-nonprod.p12 -storepass changeit

# Export public certificates for both the client and server
keytool -exportcert -alias client -file client-public.cer -keystore client-nonprod.p12 -storepass changeit

keytool -exportcert -alias server -file server-public.cer -keystore server-nonprod.p12 -storepass changeit

# Import the client and server public certificates into each others keystore/truststore
keytool -importcert -keystore client-nonprod.p12 -alias server-public-cert -file server-public.cer -storepass changeit -noprompt

keytool -importcert -keystore server-nonprod.p12 -alias client-public-cert -file client-public.cer -storepass changeit -noprompt












==============================================

# Generate a client and server RSA 2048 key pair
keytool -genkeypair -alias client -keyalg RSA -keysize 2048 -dname "CN=Client,OU=Client,O=PlumStep,L=San Francisco,S=CA,C=U" -keypass changeit -storetype PKCS12 -keystore client-keystore.p12 -storepass changeit

keytool -genkeypair -alias server -keyalg RSA -keysize 2048 -dname "CN=Server,OU=Server,O=PlumStep,L=San Francisco,S=CA,C=U" -keypass changeit -storetype PKCS12 -keystore server-keystore.p12 -storepass changeit

# Export public certificates for both the client and server
keytool -exportcert -alias client -file client-public.cer -keystore client-keystore.p12 -storepass changeit

keytool -exportcert -alias server -file server-public.cer -keystore server-keystore.p12 -storepass changeit

# Import the client and server public certificates into each others keystore/truststore
keytool -importcert -keystore client-truststore.p12 -storetype PKCS12 -alias server-public-cert -file server-public.cer -storepass changeit -noprompt

keytool -importcert -keystore server-truststore.p12 -storetype PKCS12 -alias client-public-cert -file client-public.cer -storepass changeit -noprompt


# keytool -import -v -trustcacerts -alias <your.domain> -file /path/to/your/certificate/<your.domain>.crt -keystore <JAVA_HOME>/jre/lib/security/cacerts -keypass yourpass -storepass yourpass


# Verify keystore/truststore
keytool -list -v -keystore client-keystore.p12 -storepass changeit

keytool -list -v -keystore client-truststore.p12 -storepass changeit

keytool -list -v -keystore server-keystore.p12 -storepass changeit

keytool -list -v -keystore server-truststore.p12 -storepass changeit


























=====================================================


# Generate a client and server RSA 2048 key pair
keytool -genkeypair -alias client -keyalg RSA -keysize 2048 -dname "CN=*,OU=Client,O=PlumStep,L=San Francisco,S=CA,C=U" -keypass changeit -storetype JKS -keystore client-keystore.jks -storepass changeit

keytool -genkeypair -alias server -keyalg RSA -keysize 2048 -dname "CN=*,OU=Server,O=PlumStep,L=San Francisco,S=CA,C=U" -keypass changeit -storetype JKS -keystore server-keystore.jks -storepass changeit

# Export public certificates for both the client and server
keytool -exportcert -alias client -file client-public.cer -keystore client-keystore.jks -storepass changeit

keytool -exportcert -alias server -file server-public.cer -keystore server-keystore.jks -storepass changeit

# Import the client and server public certificates into each others keystore/truststore
keytool -importcert -keystore client-truststore.jks -storetype JKS -alias server-public-cert -file server-public.cer -storepass changeit -noprompt

keytool -importcert -keystore server-truststore.jks -storetype JKS -alias client-public-cert -file client-public.cer -storepass changeit -noprompt


# Verify keystore/truststore
keytool -list -v -keystore client-keystore.jks -storepass changeit

keytool -list -v -keystore client-truststore.jks -storepass changeit

keytool -list -v -keystore server-keystore.jks -storepass changeit

keytool -list -v -keystore server-truststore.jks -storepass changeit