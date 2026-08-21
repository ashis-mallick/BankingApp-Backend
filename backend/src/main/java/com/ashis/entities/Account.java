package com.ashis.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Account {


    @Id
    private Long customerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customer customer;

    @Column(unique = true, nullable = false)
    private String customerAccountNo;

    @Column(nullable = false)
    private String customerPassword;

    @Column(nullable = false)
    private LocalDateTime customerCreatedAt;

    private BigDecimal totalAmount;



}
