package com.example.tls.demo.client.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/client/webflux")
    public String getMessage() {
        return "CLIENT-WEB-FLUX";
    }
}
