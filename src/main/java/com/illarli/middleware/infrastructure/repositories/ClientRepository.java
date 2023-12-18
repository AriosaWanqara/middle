package com.illarli.middleware.infrastructure.repositories;

import com.illarli.middleware.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}