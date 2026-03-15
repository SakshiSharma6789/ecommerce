package com.ecommerce.ecommerce.entity;

import com.ecommerce.ecommerce.enums.Roles;
import jakarta.persistence.*;
import lombok.*;


@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false , unique = true)
    private Roles authority;

}
