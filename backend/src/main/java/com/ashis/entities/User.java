package com.ashis.entities;

import com.ashis.utils.Roles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;

    @Column(nullable = false)
    private boolean enabled = true;

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customer customer;
}