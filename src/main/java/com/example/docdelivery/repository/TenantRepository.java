package com.example.docdelivery.repository;

import com.example.docdelivery.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant,Integer> {

}
