package com.example.tls.client.web.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TlsClientWebDemoApplication {

	public static void main(String[] args) {
//		configureRestClientTrustStore();
		SpringApplication.run(TlsClientWebDemoApplication.class, args);
	}


	private static void configureRestClientTrustStore() {
		System.setProperty("javax.net.debug", "all");
		System.setProperty("javax.net.ssl.trustStore", "/absolute/path/to/keystores/client-truststore.p12");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");
		System.setProperty("javax.protocol", "TLSv1.3");

		// Or Pass the properties as JVM Arguments For REST Client
		// -Djavax.net.debug=all
		// -Djavax.net.ssl.trustStore="/absolute/path/to/key stores/client-truststore.p12"
		// -Djavax.net.ssl.trustStorePassword=changeit
		// -Djavax.net.ssl.trustStoreType=PKCS12
		// -Djavax.protocol=TLSv1.3
	}

}
