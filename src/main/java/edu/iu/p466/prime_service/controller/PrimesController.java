package edu.iu.p466.prime_service.controller;

import edu.iu.p466.prime_service.service.IPrimeService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/primes")
public class PrimesController {
    private final IPrimeService primesService;

    public PrimesController(IPrimeService primesService) {
        this.primesService = primesService;
    }

    @GetMapping("/{n}")
    public boolean isPrime(@PathVariable int n) {
        return primesService.isPrime(n);
    }

}
