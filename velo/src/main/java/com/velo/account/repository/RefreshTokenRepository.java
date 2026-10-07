package com.velo.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velo.account.domain.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Object>{

}
