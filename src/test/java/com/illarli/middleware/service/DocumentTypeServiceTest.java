package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.DocumentTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DocumentTypeServiceTest {
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Test
    void test_document_type_is_not_empty() {
        Assertions.assertTrue(documentTypeRepository.findAll().size() > 0, "List is empty");
    }
}