package com.ashis.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDto {

    private String customerFirstName;

    private String customerLastName;

    private String customerAddress;

    private String customerEmail;

    private String customerPhone;

    private LocalDate customerDateOfBirth;

    private String userId;

    private String customerPassword;






}