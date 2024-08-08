package com.example.docdelivery.repository;

import com.example.docdelivery.entity.ApplicationUser;
import com.example.docdelivery.entity.Rentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentorRepository extends JpaRepository<Rentor, Integer> {

    Optional<Rentor> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Rentor> findByUser(ApplicationUser user);
}
