package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.entity.VerificationToken;
import com.ecommerce.ecommerce.repositories.UserRepository;
import com.ecommerce.ecommerce.repositories.VerificationTokenRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Builder
public class VerificationTokenService {
    private final VerificationTokenRepo verificationTokenRepo;
    private final EmailService emailService;

    public void createAndSendToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusHours(24))
                .used(false)
                .build();
        verificationTokenRepo.save(verificationToken);
        String verificationLink = "http://localhost:8080/auth/verify?token=" + token;

        String subject = "verify your email";
        String body = "Hello " + user.getFirstName() + ",\n\n"
                + "Please click the link below to verify your email:\n"
                + verificationLink + "\n\n"
                + "This link will expire in 24 hours.";

        emailService.sendEmail(user.getEmail() , subject , body);

    }
}
