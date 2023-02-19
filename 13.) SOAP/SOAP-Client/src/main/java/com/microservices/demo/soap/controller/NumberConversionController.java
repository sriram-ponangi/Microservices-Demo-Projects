package com.microservices.demo.soap.controller;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xjc.com.dataaccess.webservicesserver.NumberToDollarsResponse;
import xjc.com.dataaccess.webservicesserver.NumberToWordsResponse;
import com.microservices.demo.soap.client.NumberConversionWSDLClient;

@RestController
public class NumberConversionController {
	@Autowired
	NumberConversionWSDLClient client;
	
	@GetMapping(value = "/toWords/{number}" , produces = "application/json; charset=utf-8")	
	@ResponseBody NumberToWordsResponse numberToWordsResponse(@PathVariable("number") BigInteger number) {
		NumberToWordsResponse response = client.getNumberToWords(number);
		return response;
	}
	
	@GetMapping(value = "/toDollars/{number}" , produces = "application/json; charset=utf-8")	
	@ResponseBody NumberToDollarsResponse numberToDollarsResponse(@PathVariable("number") BigDecimal number) {
		NumberToDollarsResponse response = client.getNumberToDollars(number);
		return response;
	}
}