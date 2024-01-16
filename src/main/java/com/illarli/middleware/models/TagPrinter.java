package com.illarli.middleware.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TagPrinter {
    @Id
    private String id;
    private String name;

    protected TagPrinter() {
    }

    public TagPrinter(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
