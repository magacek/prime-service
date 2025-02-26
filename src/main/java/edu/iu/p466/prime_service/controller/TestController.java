package edu.iu.p466.prime_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/public")
    public Map<String, String> publicEndpoint() {
        return Map.of("status", "OK", "message", "This is a public endpoint");
    }

    @GetMapping("/protected")
    public Map<String, String> protectedEndpoint() {
        return Map.of("status", "OK", "message", "This is a protected endpoint");
    }

    @PostMapping("/register-test")
    public Map<String, String> registerTest() {
        return Map.of("status", "OK", "message", "Registration test endpoint");
    }
} 