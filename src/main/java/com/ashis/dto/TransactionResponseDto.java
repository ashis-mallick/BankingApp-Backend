package com.ashis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransactionResponseDto {
    private String customerAccountNo;
    private String CustomerName;
    private String transactionId;
    private String status;

    private BigDecimal availableBalance;

}
