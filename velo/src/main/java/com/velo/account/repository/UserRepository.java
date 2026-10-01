package com.velo.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velo.account.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

    boolean existsByEmail(String email);
}
