package edu.iu.p466.prime_service.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrimeServiceTest {
    private final PrimeService primeService = new PrimeService();

    @Test
    void twoIsPrime() {
        assertTrue(primeService.isPrime(2));
    }

    @Test
    void threeIsPrime() {
        assertTrue(primeService.isPrime(3));
    }

    @Test
    void fourIsNotPrime() {
        assertFalse(primeService.isPrime(4));
    }

    @Test
    void fortyFiveIsNotPrime() {
        assertFalse(primeService.isPrime(45));
    }
} 