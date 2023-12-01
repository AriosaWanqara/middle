package com.illarli.middleware.infrastructure.repositories;

import com.illarli.middleware.models.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterRepository extends JpaRepository<Printer, String> {
}