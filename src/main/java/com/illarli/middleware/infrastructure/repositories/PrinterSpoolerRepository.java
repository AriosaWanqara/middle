package com.illarli.middleware.infrastructure.repositories;

import com.illarli.middleware.models.PrinterSpooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterSpoolerRepository extends JpaRepository<PrinterSpooler, String> {
}