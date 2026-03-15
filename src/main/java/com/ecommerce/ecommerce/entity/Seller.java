package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "sellers")
@NoArgsConstructor
@AllArgsConstructor
public class Seller extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false , unique = true)
    private String gst;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String companyContact;

    @Column(nullable = false , unique = true , length = 500)
    private String companyAddress;

    @Column(nullable = false)
    private boolean isCompanyActive;

    @OneToOne
    @JoinColumn(nullable = false , name = "user_id" , unique = true)
    private User user;
}
