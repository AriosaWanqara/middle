package com.illarli.middleware.infrastructure.repositories;

import com.illarli.middleware.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}