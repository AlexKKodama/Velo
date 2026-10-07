package com.velo.account.dto;

public record LoginResponse(
    String accessToken,
    String refreshToken
) {}
