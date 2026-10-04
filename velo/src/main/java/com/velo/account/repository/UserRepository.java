package com.velo.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velo.account.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
