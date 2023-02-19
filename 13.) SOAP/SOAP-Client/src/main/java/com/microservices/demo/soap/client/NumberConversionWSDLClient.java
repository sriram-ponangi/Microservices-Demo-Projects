package com.microservices.demo.soap.client;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.PostConstruct;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import xjc.com.dataaccess.webservicesserver.NumberToDollars;
import xjc.com.dataaccess.webservicesserver.NumberToDollarsResponse;
import xjc.com.dataaccess.webservicesserver.NumberToWords;
import xjc.com.dataaccess.webservicesserver.NumberToWordsResponse;
import xjc.com.dataaccess.webservicesserver.ObjectFactory;

@Component
public class NumberConversionWSDLClient extends WebServiceGatewaySupport{
	
	@PostConstruct
	private void config(){
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("xjc.com.dataaccess.webservicesserver");
		getWebServiceTemplate().setDefaultUri("http://www.dataaccess.com/webservicesserver/numberconversion.wso");
		getWebServiceTemplate().setMarshaller(marshaller);
		getWebServiceTemplate().setUnmarshaller(marshaller);
	}

	public NumberToWordsResponse getNumberToWords(BigInteger n) {
		NumberToWords request =  new ObjectFactory().createNumberToWords(); //new NumberToWords();
		request.setUbiNum(n);		
		
		NumberToWordsResponse response = (NumberToWordsResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.dataaccess.com/webservicesserver/numberconversion.wso",request);
		
		return response;
	}
	
	public NumberToDollarsResponse getNumberToDollars(BigDecimal n) {
		NumberToDollars request = new ObjectFactory().createNumberToDollars();// new NumberToDollars();
		request.setDNum(n);
		
		NumberToDollarsResponse response = (NumberToDollarsResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.dataaccess.com/webservicesserver/numberconversion.wso",request);
		
		return response;
	}
}
