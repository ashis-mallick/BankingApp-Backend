package com.ashis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AccountBalanceRespnse {

    private String customerAccountNo;
    private BigDecimal totalBalance;
    private LocalDate date;


}
