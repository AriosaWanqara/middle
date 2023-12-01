package com.illarli.middleware.models;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CashDrawer {
    @Id
    private String id;
    @Nullable
    private String port;
    @Nullable
    private String printer;
    private boolean usb;

    public CashDrawer(String id, @Nullable String port, @Nullable String printer, boolean usb) {
        this.id = id;
        this.port = port;
        this.printer = printer;
        this.usb = usb;
    }

    protected CashDrawer() {
    }

    public String getId() {
        return id;
    }

    @Nullable
    public String getPort() {
        return port;
    }

    @Nullable
    public String getPrinter() {
        return printer;
    }

    public boolean isUsb() {
        return usb;
    }
}
