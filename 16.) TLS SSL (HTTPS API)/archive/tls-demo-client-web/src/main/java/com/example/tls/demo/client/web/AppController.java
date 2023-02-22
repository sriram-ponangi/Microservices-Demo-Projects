package com.example.tls.demo.client.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AppController {


    @GetMapping("/client/web")
    public String getMessage() {
        return "CLIENT-WEB";
    }

    @GetMapping("/client/web2")
    public String getMessage2() {
        String response = new RestTemplate().getForObject("https://127.0.0.1:8443/server/web", String.class);
        return "CLIENT-WEB" + "\n"+ response;
    }
}
