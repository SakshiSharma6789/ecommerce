package com.ecommerce.ecommerce.config;

import com.ecommerce.ecommerce.entity.Role;
import com.ecommerce.ecommerce.enums.Roles;
import com.ecommerce.ecommerce.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {


    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByAuthority(Roles.ROLE_ADMIN).isEmpty()) {
            roleRepository.save(Role.builder().authority(Roles.ROLE_ADMIN).build());
        }

        if (roleRepository.findByAuthority(Roles.ROLE_CUSTOMER).isEmpty()) {
            roleRepository.save(Role.builder().authority(Roles.ROLE_CUSTOMER).build());
        }

        if (roleRepository.findByAuthority(Roles.ROLE_SELLER).isEmpty()) {
            roleRepository.save(Role.builder().authority(Roles.ROLE_SELLER).build());
        }


    }
}
