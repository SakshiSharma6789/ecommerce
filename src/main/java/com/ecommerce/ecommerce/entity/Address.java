package com.ecommerce.ecommerce.entity;

import com.ecommerce.ecommerce.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;
import org.apache.el.stream.Optional;

import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String addressLine;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String zipCode;



    private String label;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressType addressType;

    @ManyToOne
    @JoinColumn(name = "use_id" , nullable = false)
    private User user;

}
