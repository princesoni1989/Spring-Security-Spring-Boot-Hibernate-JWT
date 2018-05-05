package com.spring.boot_security_hibernate.dao.repository;

import com.spring.boot_security_hibernate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   Optional<User> findUserByUsername(String username);
}

