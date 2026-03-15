package com.ecommerce.ecommerce.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegisterRequest {

    @NotBlank(message = "First name is required")
    @Size(max =50 , message = "First name must not be exceed 50 characters")
    private String firstName;

    @Size(max=50 , message = "Middle name must not be exceed 50 characters")
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Size(max=50 , message="Last name must not exceed 50 characters")
    private String lastName;

    @NotBlank(message = "email is required")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    @NotBlank
    @Pattern(regexp = "^[6-9]\\d{9}$" , message = "Invalid contact number")
    private String contact;


    @NotEmpty(message = "Atleast one address is required")
    private List<@Valid AddressRequest> addresses;

}
