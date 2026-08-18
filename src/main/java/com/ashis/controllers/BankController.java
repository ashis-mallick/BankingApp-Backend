package com.ashis.controllers;

import com.ashis.dto.RegisterDto;
import com.ashis.dto.RegisterResponseDto;
import com.ashis.services.BankService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    public BankController(BankService bankService) {

        this.bankService = bankService;
    }

    private BankService bankService;


    @PostMapping("/register")
    public RegisterResponseDto openRegisterPage(@RequestBody RegisterDto registerDto){

             return bankService.saveRegisterData(registerDto);

    }







}
