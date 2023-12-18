package com.illarli.middleware.resolver;


import com.illarli.middleware.models.DocumentType;
import com.illarli.middleware.models.Printer;
import com.illarli.middleware.models.PrinterType;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PrinterDTO {
    private String id;
    @NotBlank(message = "The name field is required")
    private String name;
    @NotBlank(message = "The font size field is required")
    @Pattern(regexp = "^[AB]+", message = "The field font size should be A or B")
    @Size(min = 1, max = 1, message = "The field font size should have 1 character")
    private String fontSize;
    @Min(value = 1, message = "The copyNumber field is required")
    private int copyNumber;
    @Min(value = 1, message = "The characterNumber field is required")
    private int characterNumber;
    private String address;
    private int port;
    private int detailsType = 1;
    @NotNull(message = "The type field is required")
    private PrinterType type;
    @NotEmpty(message = "The id documentType is required")
    private List<String> documentType;

    public PrinterDTO(
            String id,
            String name,
            String fontSize,
            int copyNumber,
            int characterNumber,
            String address,
            int port,
            int detailsType,
            PrinterType type,
            List<String> documentType
    ) {
        this.id = id;
        this.name = name;
        this.fontSize = fontSize;
        this.copyNumber = copyNumber;
        this.characterNumber = characterNumber;
        this.address = address;
        this.port = port;
        this.detailsType = detailsType;
        this.type = type;
        this.documentType = documentType;
    }

    private PrinterDTO() {
    }

    public Printer createPrinter() {
        List<DocumentType> documentTypes = new ArrayList<>();
        this.documentType.forEach(s -> {
            documentTypes.add(DocumentType.create(s));
        });
        return new Printer(
                this.id != null ? this.id : UUID.randomUUID().toString(),
                this.name,
                this.fontSize,
                this.copyNumber,
                this.characterNumber,
                this.address,
                this.port,
                this.detailsType,
                this.type,
                documentTypes
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFontSize() {
        return fontSize;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public int getCharacterNumber() {
        return characterNumber;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public PrinterType getType() {
        return type;
    }

    public List<String> getDocumentType() {
        return documentType;
    }
}
