package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.SellerRegisterRequest;
import com.ecommerce.ecommerce.entity.Role;
import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.enums.Roles;
import com.ecommerce.ecommerce.exception.InvalidRequestException;
import com.ecommerce.ecommerce.exception.ResourceAlreadyExistsException;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.repositories.RoleRepository;
import com.ecommerce.ecommerce.repositories.SellerRepository;
import com.ecommerce.ecommerce.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerAuthService {
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String registerSeller(SellerRegisterRequest request){
        Role sellerRole = roleRepository.findByAuthority(Roles.ROLE_SELLER)
                .orElseThrow(() -> new ResourceNotFoundException("seller role not found"));

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);
        if(user==null){

            if(!request.getPassword().equals(request.getConfirmPassword())){
                throw new InvalidRequestException("confirm password feild must match password field");
            }
//            user.setEmail(request.getEmail());
//            user.setFirstName(request.getFirstName());
//            user.setMiddleName(request.getMiddleName());
//            user.setPassword(passwordEncoder.encode(request.getPassword()));
//            user.setLastName(request.getLastName());
//            user.setIsActive(true);

            user = User.builder()
                            .email(request.getEmail())
                                    .firstName(request.getFirstName())
                                            .middleName(request.getMiddleName())
                                                    .lastName(request.getLastName())
                    .password(request.getPassword())
                    .isActive(false)
                    .isDeleted(false)
                                                            .build();


            user.getRoles().add(sellerRole);
            user = userRepository.save(user);
        }else{
            boolean alreadySeller = user.getRoles().stream()
                    .anyMatch(role -> role.getAuthority().equals(Roles.ROLE_SELLER));

            if(alreadySeller || sellerRepository.existsByUser(user)){
                throw new ResourceAlreadyExistsException("Seller account already created");
            }
            user.getRoles().add(sellerRole);
            user = userRepository.save(user);
        }
        Seller seller = new Seller();
        seller.setCompanyName(request.getCompanyName());
        seller.setGst(request.getGst());
        seller.setCompanyContact(request.getCompanyContact());
        seller.setCompanyActive(false);
        seller.setCompanyAddress(request.getCompanyAddress());
        seller.setUser(user);

        sellerRepository.save(seller);


        return "Seller registered successfully";

    }


}
