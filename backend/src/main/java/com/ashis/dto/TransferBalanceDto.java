package com.ashis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferBalanceDto {


    @NotBlank(message = "Sender account number is required")
    private  String senderAccountNo;

    @NotBlank(message = "Receiver account number is required")
    private String receiverAccountNo;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    private String description;




}
