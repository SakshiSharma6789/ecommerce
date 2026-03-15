package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer , UUID> {

    Optional<Customer> findByUserId(UUID userId);
}
