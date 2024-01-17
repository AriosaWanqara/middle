package com.illarli.middleware.infrastructure.repositories;

import com.illarli.middleware.models.TagPrinter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagPrinterRepository extends JpaRepository<TagPrinter, String> {
}