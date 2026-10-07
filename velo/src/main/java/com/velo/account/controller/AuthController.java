package com.velo.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velo.account.dto.LoginRequest;
import com.velo.account.dto.LoginResponse;
import com.velo.account.dto.RegisterRequest;
import com.velo.account.service.LoginService;
import com.velo.account.service.RegisterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegisterService registerService;
    private final LoginService loginService;

    public AuthController(
        RegisterService registerService,
        LoginService loginService){
        this.registerService = registerService;
        this.loginService = loginService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request){
        registerService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid  @RequestBody  LoginRequest request){
        LoginResponse response = loginService.login(request);

        return ResponseEntity.ok(response);
    }
}
