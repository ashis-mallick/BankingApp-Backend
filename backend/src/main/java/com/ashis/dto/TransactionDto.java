package com.ashis.dto;

import com.ashis.utils.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {


    @NotBlank(message = "Customer account number is required")
    private String customerAccountNo;


    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    private TransactionType transactionType;

    private String transactionDescription;



}
