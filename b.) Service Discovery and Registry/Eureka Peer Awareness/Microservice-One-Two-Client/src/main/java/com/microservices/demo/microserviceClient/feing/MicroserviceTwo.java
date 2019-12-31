package com.microservices.demo.microserviceClient.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Microservice-Two")
public interface MicroserviceTwo {
	
	@RequestMapping(method = RequestMethod.GET, value = "/two/message")
    String getServiceTwoMessage();//name of the method is not relevant but the return type and parameters are
}
