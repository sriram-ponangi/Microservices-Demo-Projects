package com.example.tls.demo.client.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/client/web")
    public String getMessage() {
        return "CLIENT-WEB";
    }
}
