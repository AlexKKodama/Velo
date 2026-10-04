package com.velo.account.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.velo.account.domain.User;
import com.velo.account.dto.RegisterRequest;
import com.velo.account.exception.EmailAlreadyExistsException;
import com.velo.account.repository.UserRepository;

@Service
public class RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request){
        if(userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException();
        }

        User user = new User(
            request.username(),
            request.email(),
            passwordEncoder.encode(request.password())
        );

        userRepository.save(user);
    }

}
