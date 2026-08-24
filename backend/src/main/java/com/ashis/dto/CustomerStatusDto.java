package com.ashis.dto;

import com.ashis.utils.AccountStatus;
import lombok.Data;

@Data
public class CustomerStatusDto {

    private String accountNo;
    private AccountStatus accountStatus;

}
