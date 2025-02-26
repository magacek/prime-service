package edu.iu.p466.prime_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of("status", "UP", "message", "Prime Service is running");
    }
    
    @GetMapping("/auth/test")
    public Map<String, String> authTest() {
        return Map.of("status", "OK", "message", "Auth endpoints are accessible");
    }
}
