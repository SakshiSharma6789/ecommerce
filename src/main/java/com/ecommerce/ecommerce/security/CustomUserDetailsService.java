package com.ecommerce.ecommerce.security;

import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->
                        new UsernameNotFoundException("user not found with email : "+ email)
                );
        return new CustomUserDetails(user);



    }
}
