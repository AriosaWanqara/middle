package com.illarli.middleware.models;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CashDrawer {
    @Id
    private String id;
    @Nullable
    private String port;
    @ManyToOne()
    @JoinColumn(name = "printer_id", nullable = false)
    private Printer printer;
    private boolean usb;

    public CashDrawer(String id, @Nullable String port, Printer printer, boolean usb) {
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

    public Printer getPrinter() {
        return printer;
    }

    public boolean isUsb() {
        return usb;
    }
}
