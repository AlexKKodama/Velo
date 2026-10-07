package com.velo.account.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.HexFormat;

import org.springframework.stereotype.Service;

import com.velo.account.domain.RefreshToken;
import com.velo.account.domain.User;
import com.velo.account.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {
    private final SecureRandom secureRandom = new SecureRandom();
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository){
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String generateToken(){
        byte[] randomBytes = new byte[32];

        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(randomBytes);
    }

    public void create(User user, String refreshToken){
        Instant now = Instant.now();
        RefreshToken token = new RefreshToken(
            user,
            hashToken(refreshToken),
            now,
            now.plus(Duration.ofDays(15))
        );

        refreshTokenRepository.save(token);
    }

    private String hashToken(String token){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));

            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e){
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }
}
