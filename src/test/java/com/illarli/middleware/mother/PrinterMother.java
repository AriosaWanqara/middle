package com.illarli.middleware.mother;

import com.illarli.middleware.models.DocumentType;
import com.illarli.middleware.models.DocumentTypeEnum;
import com.illarli.middleware.models.Printer;
import com.illarli.middleware.models.PrinterType;

import java.util.ArrayList;
import java.util.List;

public class PrinterMother {
    public static Printer create(String id) {
        ArrayList<DocumentType> documentTypes = new ArrayList<>(List.of(
                new DocumentType("0fa2a879-866b-4f7a-8bdb-1c850fd80437", "Factura Electrónica", DocumentTypeEnum.ELECTRONIC_INVOICE.getCode())
        ));
        return new Printer(
                id != null ? id : "1",
                "DonnQ",
                "A",
                1,
                64,
                null,
                9100,
                1,
                PrinterType.WIFI,
                documentTypes
        );
    }
}
