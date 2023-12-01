package com.illarli.middleware.infrastructure.repositories;

import com.illarli.middleware.models.BalanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceTypeRepository extends JpaRepository<BalanceType, String> {
}