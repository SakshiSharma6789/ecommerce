package com.ecommerce.ecommerce.repositories;
import com.ecommerce.ecommerce.entity.Role;
import com.ecommerce.ecommerce.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByAuthority(Roles authority);
}
