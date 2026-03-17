package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.services.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEmail {
    private final EmailService emailService;

    public TestEmail(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/test-email")
    public String testmail(){
        emailService.sendEmail(
                "sakshi.practice2002@gmail.com",
                "Test email",
                "hello my test email"
        );
        return "email sent successfully";
    }
}
