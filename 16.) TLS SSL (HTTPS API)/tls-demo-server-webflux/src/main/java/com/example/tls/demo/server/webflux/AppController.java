package com.example.tls.demo.server.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/server/webflux")
    public String getMessage() {
        return "SERVER-WEB-FLUX";
    }
}
