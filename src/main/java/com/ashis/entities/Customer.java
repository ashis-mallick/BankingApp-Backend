package com.ashis.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="customer_id")
    private Long customerId;

    @Column(nullable = false)
    private String customerFirstName;

    @Column(nullable = false)
    private String customerLastName;

    @Column(nullable = false)
    private  String customerAddress;

    @NotNull
    private String customerEmail;
    @NotNull
    private String customerPhone;

    @Column(nullable = false)
    private LocalDate customerDateOfBirth;

    @OneToMany(mappedBy = "customer")
    private List<Transactions> transactions;

    private LocalDateTime customerCreatedAt;




}
