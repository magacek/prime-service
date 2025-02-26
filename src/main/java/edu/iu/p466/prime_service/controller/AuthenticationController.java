package edu.iu.p466.prime_service.controller;

import edu.iu.p466.prime_service.model.Customer;
import edu.iu.p466.prime_service.service.IAuthenticationService;
import edu.iu.p466.prime_service.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final IAuthenticationService authenticationService;
    private final TokenService tokenService;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            IAuthenticationService authenticationService,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        logger.info("Received registration request for user: {}", customer.getUsername());
        try {
            Customer registeredCustomer = authenticationService.register(customer);
            logger.info("Successfully registered user: {}", registeredCustomer.getUsername());
            return ResponseEntity.ok(registeredCustomer);
        } catch (IOException e) {
            logger.error("Error during registration: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            logger.error("Unexpected error during registration: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(Map.of("error", "An unexpected error occurred"));
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Customer customer) {
        logger.info("Received login request for user: {}", customer.getUsername());
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    customer.getUsername(),
                                    customer.getPassword()));
            String token = tokenService.generateToken(authentication);
            logger.info("Successfully authenticated user: {}", customer.getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            logger.error("Error during login: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid username or password"));
        }
    }
} 