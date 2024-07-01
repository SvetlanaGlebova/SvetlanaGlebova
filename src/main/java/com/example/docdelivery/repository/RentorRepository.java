package com.example.docdelivery.repository;

import com.example.docdelivery.entity.Rentor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentorRepository extends CrudRepository<Rentor, Integer> {

}
