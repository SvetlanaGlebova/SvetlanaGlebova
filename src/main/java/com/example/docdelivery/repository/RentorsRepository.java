package com.example.docdelivery.repository;

import com.example.docdelivery.entity.Rentors;

public class RentorsRepository {

    public interface RentRepository extends JpaRepository<Rentors, Integer> {
    }
}
