package com.illarli.middleware.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TagPrinter {
    @Id
    private String id;
    private String name;
}
