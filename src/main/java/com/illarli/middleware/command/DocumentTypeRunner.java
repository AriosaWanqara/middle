package com.illarli.middleware.command;


import com.illarli.middleware.infrastructure.repositories.DocumentTypeRepository;
import com.illarli.middleware.models.DocumentType;
import com.illarli.middleware.models.DocumentTypeEnum;
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
                    new DocumentType("0fa2a879-866b-4f7a-8bdb-1c850fd80437", "Factura Electrónica", DocumentTypeEnum.ELECTRONIC_INVOICE.getCode()),
                    new DocumentType("2e61b543-1189-461b-993f-0e5443cfd349", "Factura Preimpresa", DocumentTypeEnum.PRE_TICKET.getCode()),
                    new DocumentType("9c0c3320-0300-4fee-a603-65a9c02b6502", "Recibos", DocumentTypeEnum.VOUCHER.getCode()),
                    new DocumentType("1e4b6a0c-d336-4aef-ae9f-b7042e21fd99", "Cotizaciones", DocumentTypeEnum.QUOTATION.getCode()),
                    new DocumentType("88821664-52e5-4e02-9a06-438893eea4e0", "Comanda", DocumentTypeEnum.COMMAND.getCode()),
                    new DocumentType("5b1d3400-c492-4b44-b50e-701b044b6848", "Cierre de Caja", DocumentTypeEnum.CLOSE_DRAWER.getCode())
            ));
            documentTypeRepository.saveAll(documentTypes);
        };
    }
}
