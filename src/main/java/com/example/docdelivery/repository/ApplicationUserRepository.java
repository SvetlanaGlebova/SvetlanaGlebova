package com.example.docdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.example.docdelivery.entity.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);
    boolean existsByUsername(String username);
}

