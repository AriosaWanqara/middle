package com.illarli.middleware.models;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clientName;
    private String DNI;
    @Nullable
    private String clientPhone;
    @Nullable
    private String clientAddress;

    protected Client() {
    }

    public Client(String clientName, String DNI, @Nullable String clientPhone, @Nullable String clientAddress) {
        this.clientName = clientName;
        this.DNI = DNI;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
    }

    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getDNI() {
        return DNI;
    }

    @Nullable
    public String getClientPhone() {
        return clientPhone;
    }

    @Nullable
    public String getClientAddress() {
        return clientAddress;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", DNI='" + DNI + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                '}';
    }
}
