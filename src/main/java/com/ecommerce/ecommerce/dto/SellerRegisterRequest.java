package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerRegisterRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;


    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Company contact is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Company contact must be 10 digits")
    private String companyContact;

    @NotBlank(message = "GST is required")
    private String gst;

    @NotBlank(message = "company address is required")
    private String companyAddress;

}
