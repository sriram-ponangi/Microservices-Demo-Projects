package com.microservices.demo.microserviceClient.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

	@Bean
	@LoadBalanced 
	/*
	 * While using Application-Name directly in the url Ex:RestTemplateExampleController @LoadBalanced is required
	 * But for DiscoveryClientExampleController and RibbonClientExampleController classes adding @LoadBalanced gives an error?
	 */
	
	RestTemplate createRestTemplate() {
		return new RestTemplate();
	}
	
}
