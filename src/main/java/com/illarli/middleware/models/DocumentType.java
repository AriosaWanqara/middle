package com.illarli.middleware.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class DocumentType {
    @Id
    private String id;
    public String name;
    public String code;

    public DocumentType(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    protected DocumentType() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentType that = (DocumentType) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }

    public static DocumentType create(String id) {
        DocumentType documentType = new DocumentType();
        documentType.id = id;
        return documentType;
    }
}
