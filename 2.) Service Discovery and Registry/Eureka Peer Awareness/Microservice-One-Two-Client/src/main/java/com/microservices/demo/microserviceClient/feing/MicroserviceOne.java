package com.microservices.demo.microserviceClient.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Microservice-One")
public interface MicroserviceOne {
	
	@RequestMapping(method = RequestMethod.GET, value = "/one/message")
    String getServiceOneMessage();//name of the method is not relevant but the return type and parameters are
}
