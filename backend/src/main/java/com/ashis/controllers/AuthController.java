package com.ashis.controllers;

import com.ashis.dto.RegisterDto;
import com.ashis.dto.RegisterResponseDto;
import com.ashis.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    private final AuthService authService;


    @PostMapping("/register")
    public RegisterResponseDto openRegisterPage( @Valid @RequestBody RegisterDto registerDto){

             return authService.saveRegisterData(registerDto);

    }



}
