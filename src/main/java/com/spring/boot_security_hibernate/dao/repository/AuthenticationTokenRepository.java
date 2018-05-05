package com.spring.boot_security_hibernate.dao.repository;

import com.spring.boot_security_hibernate.entity.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    public AuthenticationToken findAuthenticationTokenByToken(String token);
}
