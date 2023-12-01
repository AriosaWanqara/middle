package com.illarli.middleware.infrastructure.repositories;

import com.illarli.middleware.models.CashDrawer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashDrawerRepository extends JpaRepository<CashDrawer, String> {
}