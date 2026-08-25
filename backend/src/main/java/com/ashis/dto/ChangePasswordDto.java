package com.ashis.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChangePasswordDto {

    private String accountNo;

    private String newPassword;

    private String description;


}
