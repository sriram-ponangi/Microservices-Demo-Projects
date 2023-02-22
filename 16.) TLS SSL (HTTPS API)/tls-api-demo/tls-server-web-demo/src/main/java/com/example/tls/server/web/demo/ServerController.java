package com.example.tls.server.web.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServerController {

    @GetMapping("/test")
    public String getTestMessage() {
        return "ACCESSED - WEB-SERVER-TLS-DEMO";
    }

    @GetMapping("/server")
    public String getClientMessage() {
        RestTemplate restTemplate = new RestTemplate();
        return "WEB-SERVER-TLS-DEMO" + " :: " + restTemplate.getForObject("https://localhost:8441/test", String.class);
    }
}
