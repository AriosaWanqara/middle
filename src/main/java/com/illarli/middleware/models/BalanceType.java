package com.illarli.middleware.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BalanceType {
    @Id
    private String id;
    private String name;
    private int BaudRate;
    private int DataBits;
    private int StopBits;
    private int Parity;
    private byte[] Command;

    public BalanceType(String id, String name, int baudRate, int dataBits, int stopBits, int parity, byte[] command) {
        this.id = id;
        this.name = name;
        this.BaudRate = baudRate;
        this.DataBits = dataBits;
        this.StopBits = stopBits;
        this.Parity = parity;
        this.Command = command;
    }

    protected BalanceType() {
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return this.id;
    }

    public String getPort() {
        return this.name;
    }

    public int getBaudRate() {
        return this.BaudRate;
    }

    public int getDataBits() {
        return this.DataBits;
    }

    public int getStopBits() {
        return this.StopBits;
    }

    public int getParity() {
        return this.Parity;
    }

    public byte[] getCommand() {
        return this.Command;
    }
}
