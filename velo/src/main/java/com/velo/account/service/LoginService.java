package com.velo.account.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.velo.account.domain.User;
import com.velo.account.dto.LoginRequest;
import com.velo.account.dto.LoginResponse;
import com.velo.account.exception.InvalidCredentialsException;
import com.velo.account.repository.UserRepository;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public LoginService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.email())
                    .orElseThrow(InvalidCredentialsException::new);

        if(!passwordEncoder.matches(request.password(), user.getHash())){
            throw new InvalidCredentialsException();
        }

        String accessToken = jwtService.generateToken(user);

        String refreshToken = refreshTokenService.generateToken();

        refreshTokenService.create(user, refreshToken);

        return new LoginResponse(
            accessToken,
            refreshToken
        );

    }
}
