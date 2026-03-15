package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<Seller , UUID> {
    Optional<Seller> findByUserId(UUID userId);
    boolean existsByUser(User user);
}
