package com.illarli.middleware.infrastructure.repositories;

import com.illarli.middleware.models.Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository extends JpaRepository<Details, Long> {
}