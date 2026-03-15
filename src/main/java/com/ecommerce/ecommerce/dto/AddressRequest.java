package com.ecommerce.ecommerce.dto;


import com.ecommerce.ecommerce.enums.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequest {
    @NotBlank(message = "Address line is required")
    @Size(min = 5, max = 255, message = "Address line must be between 5 and 255 characters")
    private String addressLine;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid zip code")
    private String zipCode;

    @Size(max = 50, message = "Label must not exceed 50 characters")
    private String label;

    @NotNull(message = "Address type is required")
    private AddressType addressType;
}
