package com.illarli.middleware.command;


import com.illarli.middleware.infrastructure.repositories.DocumentTypeRepository;
import com.illarli.middleware.models.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentTypeRunner {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    public DocumentTypeRunner(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    @Bean
    CommandLineRunner documentTypeSeder() {
        return args -> {
            ArrayList<DocumentType> documentTypes = new ArrayList<>(List.of(
                    new DocumentType("0fa2a879-866b-4f7a-8bdb-1c850fd80437", "Factura Electrónica", "01"),
                    new DocumentType("2e61b543-1189-461b-993f-0e5443cfd349", "Factura Preimpresa", "02"),
                    new DocumentType("9c0c3320-0300-4fee-a603-65a9c02b6502", "Recibos", "03"),
                    new DocumentType("1e4b6a0c-d336-4aef-ae9f-b7042e21fd99", "Cotizaciones", "04"),
                    new DocumentType("88821664-52e5-4e02-9a06-438893eea4e0", "Cierres de cajas y otros", "05")
            ));
            documentTypeRepository.saveAll(documentTypes);
        };
    }
}
