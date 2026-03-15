package com.ecommerce.ecommerce.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message="username is required")
    private String username;

    @NotBlank(message = "username is required")
    private String password;
}
