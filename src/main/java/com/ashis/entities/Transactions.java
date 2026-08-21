package com.ashis.entities;

import com.ashis.utils.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
public class Transactions {

    @Id
    private String TransactionId;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @ToString.Exclude
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String description;

    private String status;


}
