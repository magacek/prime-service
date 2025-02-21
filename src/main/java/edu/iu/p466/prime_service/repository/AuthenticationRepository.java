package edu.iu.p466.prime_service.repository;

import edu.iu.p466.prime_service.model.Customer;
import java.io.IOException;

public interface AuthenticationRepository {
    boolean save(Customer customer) throws IOException;
    Customer findByUsername(String username) throws IOException;
} 