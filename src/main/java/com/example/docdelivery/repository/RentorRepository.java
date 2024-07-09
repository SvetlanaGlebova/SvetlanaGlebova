package com.example.docdelivery.repository;

import com.example.docdelivery.entity.Rentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentorRepository extends JpaRepository<Rentor, Integer> {

}
