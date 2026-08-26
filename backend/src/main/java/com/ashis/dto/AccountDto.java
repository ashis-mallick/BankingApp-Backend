package com.ashis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDto {

    @NotBlank(message = "Customer account number is required")
    private String customerAccountNo;

    @Size(min = 8, message = "Password must be at least 8 characters")
    @NotBlank(message = "password is required")
    private String customerPassword;

}
