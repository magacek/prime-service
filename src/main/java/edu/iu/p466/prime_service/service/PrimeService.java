package edu.iu.p466.prime_service.service;

import org.springframework.stereotype.Service;

@Service
public class PrimeService implements IPrimeService {
    @Override
    public boolean isPrime(long n) {
        // Handle edge cases
        if (n <= 1) return false; // 1 and negative numbers are not prime
        if (n == 2 || n == 3) return true; // 2 and 3 are prime
        if (n % 2 == 0) return false; // Even numbers greater than 2 are not prime
        
        // Check odd divisors up to the square root of n
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
} 