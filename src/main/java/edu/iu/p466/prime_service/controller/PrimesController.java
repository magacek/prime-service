package edu.iu.p466.prime_service.controller;

import edu.iu.p466.prime_service.service.IPrimeService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/primes")
public class PrimesController {
    private final IPrimeService primesService;

    public PrimesController(IPrimeService primesService) {
        this.primesService = primesService;
    }

    @GetMapping("/{n}")
    public Map<String, Boolean> isPrime(@PathVariable int n) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("isPrime", primesService.isPrime(n));
        return response;
    }

}
