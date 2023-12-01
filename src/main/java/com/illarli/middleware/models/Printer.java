package com.illarli.middleware.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Printer {
    @Id
    private String id;
    private String name;
    private String fontSize;
    private int copyNumber;
    private int characterNumber;
    @Nullable
    private String address;
    @Nullable
    private int port;
    private PrinterType type;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "document_printer", joinColumns = @JoinColumn(
            name = "printerId", referencedColumnName = "id"
    ), inverseJoinColumns = @JoinColumn(
            name = "documentId", referencedColumnName = "id"
    ))
    private List<DocumentType> documentType;

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

    @Nullable
    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public PrinterType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Printer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fontSize='" + fontSize + '\'' +
                ", copyNumber=" + copyNumber +
                ", characterNumber=" + characterNumber +
                ", address='" + address + '\'' +
                ", port=" + port +
                ", type=" + type +
                '}';
    }

    public List<DocumentType> getDocumentType() {
        return documentType;
    }

    public Printer(
            String id,
            String name,
            String fontSize,
            int copyNumber,
            int characterNumber,
            @Nullable String address,
            int port,
            PrinterType type,
            List<DocumentType> documentType
    ) {
        this.id = id;
        this.name = name;
        this.fontSize = fontSize;
        this.copyNumber = copyNumber;
        this.characterNumber = characterNumber;
        this.address = address;
        this.port = port;
        this.type = type;
        this.documentType = documentType;
    }

    protected Printer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Printer printer = (Printer) o;
        return copyNumber == printer.copyNumber && characterNumber == printer.characterNumber && port == printer.port && Objects.equals(id, printer.id) && Objects.equals(name, printer.name) && Objects.equals(fontSize, printer.fontSize) && Objects.equals(address, printer.address) && type == printer.type && Objects.equals(documentType, printer.documentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fontSize, copyNumber, characterNumber, address, port, type, documentType);
    }

}
