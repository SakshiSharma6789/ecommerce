package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken , UUID> {

    Optional<VerificationToken> findByToken(String token);

    Optional<VerificationToken> findByUser(User user);


}
