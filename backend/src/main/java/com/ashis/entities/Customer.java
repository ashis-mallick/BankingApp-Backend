package com.ashis.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @Column(nullable = false)
    private String customerFirstName;

    @Column(nullable = false)
    private String customerLastName;

    @Column(nullable = false)
    private String customerAddress;

    @Column(nullable = false, unique = true)
    private String customerEmail;

    @Column(nullable = false, unique = true)
    private String customerPhone;

    @Column(nullable = false)
    private LocalDate CustomerDateOfBirth;

    private LocalDateTime customerCreatedAt;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @ToString.Exclude
    private User user;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Account account;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<Transactions> transactions;

}