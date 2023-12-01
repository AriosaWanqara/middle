package com.illarli.middleware.infrastructure.repositories;

import com.illarli.middleware.models.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, String> {
}