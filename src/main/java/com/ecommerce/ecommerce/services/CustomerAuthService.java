package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.AddressRequest;
import com.ecommerce.ecommerce.dto.CustomerRegisterRequest;
import com.ecommerce.ecommerce.entity.Address;
import com.ecommerce.ecommerce.entity.Customer;
import com.ecommerce.ecommerce.entity.Role;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.enums.Roles;
import com.ecommerce.ecommerce.exception.InvalidRequestException;
import com.ecommerce.ecommerce.exception.ResourceAlreadyExistsException;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.repositories.AddressRepo;
import com.ecommerce.ecommerce.repositories.CustomerRepository;
import com.ecommerce.ecommerce.repositories.RoleRepository;
import com.ecommerce.ecommerce.repositories.UserRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CustomerAuthService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepo addressRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public String registerCustomer(CustomerRegisterRequest rqst) {

        Role customerRole = roleRepository.findByAuthority(Roles.ROLE_CUSTOMER)
                .orElseThrow(() -> new ResourceNotFoundException("Customer role not found"));

        Optional<User> existingUser = userRepository.findByEmail(rqst.getEmail());
        User user;
        if (existingUser.isPresent()) {
            user = existingUser.get();

            boolean alreadyCustomer = user.getRoles()
                    .stream()
                    .anyMatch(role -> role.getAuthority() == Roles.ROLE_CUSTOMER);

            if (alreadyCustomer) {
                throw new ResourceAlreadyExistsException("customer account is already exist");
            }
            user.getRoles().add(customerRole);
            userRepository.save(user);
        }
        else{
            if(!rqst.getPassword().equals(rqst.getConfirmPassword())){
                throw new InvalidRequestException("Confirm password field must match to Password field");
            }
            user = User.builder()
                    .firstName(rqst.getFirstName())
                    .middleName(rqst.getMiddleName())
                    .lastName(rqst.getLastName())
                    .email(rqst.getEmail())
                    .password(passwordEncoder.encode(rqst.getPassword()))
                    .isActive(false)
                    .isDeleted(false)
                    .build();

            user.getRoles().add(customerRole);
            user=userRepository.save(user);

        }

        Customer customer = Customer.builder()
                .contact(rqst.getContact())
                .user(user)
                .build();

        customerRepository.save(customer);

        for(AddressRequest addressRequest : rqst.getAddresses()){
            Address address = Address.builder()
                    .addressLine(addressRequest.getAddressLine())
                    .city(addressRequest.getCity())
                    .state(addressRequest.getState())
                    .country(addressRequest.getCountry())
                    .label(addressRequest.getLabel())
                    .zipCode(addressRequest.getZipCode())
                    .addressType(addressRequest.getAddressType())
                    .user(user)
                    .build();

            addressRepo.save(address);

        }

        return "customer registered successfully";

    }

}
