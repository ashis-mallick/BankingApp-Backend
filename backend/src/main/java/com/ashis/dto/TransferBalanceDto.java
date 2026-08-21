package com.ashis.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferBalanceDto {

    private  String senderAccountNo;
    private String receiverAccountNo;

    private BigDecimal amount;

    private String description;




}
