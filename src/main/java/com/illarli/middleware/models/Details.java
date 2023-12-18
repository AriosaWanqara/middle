package com.illarli.middleware.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String keyName;
    private Double keyValue;

    protected Details() {
    }

    public Details(String key, Double value) {
        this.keyName = key;
        this.keyValue = value;
    }

    public Long getId() {
        return id;
    }

    public String getKeyName() {
        return keyName;
    }

    public Double getKeyValue() {
        return keyValue;
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", keyName='" + keyName + '\'' +
                ", keyValue=" + keyValue +
                '}';
    }
}
