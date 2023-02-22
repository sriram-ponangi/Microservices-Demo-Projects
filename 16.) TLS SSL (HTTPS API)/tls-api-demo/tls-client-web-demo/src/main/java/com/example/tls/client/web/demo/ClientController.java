package com.example.tls.client.web.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {

    @GetMapping("/test")
    public String getTestMessage() {
        return "ACCESSED - WEB-CLIENT-TLS-DEMO";
    }

    @GetMapping("/client")
    public String getClientMessage() {
        RestTemplate restTemplate = new RestTemplate();
        return "WEB-CLIENT-TLS-DEMO" + " :: " + restTemplate.getForObject("https://localhost:8443/test", String.class);
    }
}
