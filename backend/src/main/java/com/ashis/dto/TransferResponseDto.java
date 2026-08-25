package com.ashis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class TransferResponseDto {

    private  String senderAccountNo;

    private String recieverAccountNo;

    private BigDecimal amount;

    private String description;

    private String Status;

    private LocalDate  date;

}
