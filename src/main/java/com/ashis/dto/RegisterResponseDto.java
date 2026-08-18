package com.ashis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {

    private String customerFirstName;
    private String customerLastName;
    private String customerAccountNo;
    private Long customerId;
    private  String message;



}
