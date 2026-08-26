package com.ashis.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDto {

    @NotBlank(message = "firstname must not null ")
    private String customerFirstName;


    @NotBlank(message = "lastname must not null ")
    private String customerLastName;


    @NotBlank(message = "provide proper address  ")
    private String customerAddress;


    @Email(message = "invalid mail")
    @NotBlank(message = "email field should not empty")
    private String customerEmail;


    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @NotBlank(message = "phone no is mandatory")
    private String customerPhone;


    @NotNull(message = "provide date of birth")
    private LocalDate customerDateOfBirth;


    @NotBlank(message = "user id is required ")
    private String userId;


    @Size(min = 8, message = "Password must be at least 8 characters")
    @NotBlank(message = "password is required")
    private String customerPassword;






}