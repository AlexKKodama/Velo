package com.velo.account.dto;

public record RegisterRequest(
    String username,
    String email,
    String password
) {}
