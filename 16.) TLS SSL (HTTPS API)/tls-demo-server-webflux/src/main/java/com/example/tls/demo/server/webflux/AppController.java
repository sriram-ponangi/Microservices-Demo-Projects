package com.example.tls.demo.server.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/server/web")
    public String getMessage() {
        return "SERVER-WEB";
    }
}
