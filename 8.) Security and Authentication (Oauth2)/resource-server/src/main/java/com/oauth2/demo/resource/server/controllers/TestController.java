package com.oauth2.demo.resource.server.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    private String test(@AuthenticationPrincipal Jwt principal) {
        System.out.println("Principal :: "+ principal);
        return "Test Executed!";
    }
}
