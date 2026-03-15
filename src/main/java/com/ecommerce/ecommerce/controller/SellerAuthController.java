package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.dto.ApiResponse;
import com.ecommerce.ecommerce.dto.SellerRegisterRequest;
import com.ecommerce.ecommerce.services.SellerAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SellerAuthController {
    private final SellerAuthService sellerAuthService;

    @PostMapping("/register/seller")
    private ResponseEntity<ApiResponse<String>> sellerRegister(
            @Valid @RequestBody SellerRegisterRequest request
            ){
        String message = sellerAuthService.registerSeller(request);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message(message)
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);

    }
}
