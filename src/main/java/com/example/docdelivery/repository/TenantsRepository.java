package com.example.docdelivery.repository;

import com.example.docdelivery.entity.Rentors;
import com.example.docdelivery.entity.Tenant;

public class TenantsRepository {
    public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    }
}
