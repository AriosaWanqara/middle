package com.illarli.middleware.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Printer
{
    @Id
    private String id;
}
