package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.DocumentTypeRepository;
import com.illarli.middleware.models.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeService {

    @Autowired
    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public List<DocumentType> getAll() {
        return documentTypeRepository.findAll();
    }
}
