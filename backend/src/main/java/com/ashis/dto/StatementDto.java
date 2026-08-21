package com.ashis.dto;

import com.ashis.utils.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatementDto {

    private Long customerId;

    private String customerTransactionId;

    private BigDecimal amount;

    private String transactionDescription;

    private TransactionType transactionType;

    private String transactionStatus;



}
