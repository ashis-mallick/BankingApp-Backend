package com.ashis.dto;

import com.ashis.utils.AccountStatus;
import lombok.Data;

@Data
public class AccountDeletedDto {

    private String accountNo;

    private AccountStatus status;

    private  String message;



}
