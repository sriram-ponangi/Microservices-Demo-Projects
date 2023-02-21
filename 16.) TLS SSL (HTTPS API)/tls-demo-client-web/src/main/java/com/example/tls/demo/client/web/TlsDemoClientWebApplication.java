package com.example.tls.demo.client.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class TlsDemoClientWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(TlsDemoClientWebApplication.class, args);

		String response = new RestTemplate().getForObject("https://127.0.0.1:8443/server/web", String.class);
		System.out.println("CLIENT-WEB" + "\n"+ response);
	}



}
