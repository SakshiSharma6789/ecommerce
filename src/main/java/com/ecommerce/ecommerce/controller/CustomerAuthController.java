package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ApiResponse;
import com.ecommerce.ecommerce.dto.CustomerRegisterRequest;
import com.ecommerce.ecommerce.services.CustomerAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class CustomerAuthController {
    private final CustomerAuthService customerAuthService;
    @PostMapping("/register/customer")
    public ResponseEntity<ApiResponse<String>> registerCustomer(
            @Valid @RequestBody CustomerRegisterRequest rqst){
        String message = customerAuthService.registerCustomer(rqst);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message(message)
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);

    }


}
