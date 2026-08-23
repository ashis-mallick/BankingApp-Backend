package com.ashis.controllers;

import com.ashis.dto.RegisterDto;
import com.ashis.dto.RegisterResponseDto;
import com.ashis.services.AuthService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    private AuthService authService;


    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:5173")
    public RegisterResponseDto openRegisterPage(@RequestBody RegisterDto registerDto){

             return authService.saveRegisterData(registerDto);

    }



}
