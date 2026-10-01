package com.velo.account.domain;

public record User(
    String username,
    String email,
    String hash
) {}
