package com.ashis.dto;

import com.ashis.utils.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {

    private String customerAccountNo;

    private BigDecimal amount;

    private TransactionType transactionType;

    private String transactionDescription;



}
