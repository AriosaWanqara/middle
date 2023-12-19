package com.illarli.middleware.models;

import com.illarli.middleware.infrastructure.print.EscPosCoffee;
import com.illarli.middleware.resolver.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
public class PrinterSpooler {
    @Id
    private String id;
    private String employee;
    private String date;
    private String code;
    private String documentTypeId;
    @Nullable
    private String observation;
    @Nullable
    private String extraData;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Details> details;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Details> paymentMethods;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Client client;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Company company;

    protected PrinterSpooler() {
    }

    public PrinterSpooler(
            String id,
            String employee,
            String date,
            String code,
            String documentTypeId,
            @Nullable String observation,
            @Nullable String extraData,
            List<Details> details,
            List<Details> paymentMethods,
            Client client,
            List<Product> products,
            Company company
    ) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.code = code;
        this.documentTypeId = documentTypeId;
        this.observation = observation;
        this.extraData = extraData;
        this.details = details;
        this.paymentMethods = paymentMethods;
        this.client = client;
        this.products = products;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public String getEmployee() {
        return employee;
    }

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public String getDocumentTypeId() {
        return documentTypeId;
    }

    @Nullable
    public String getObservation() {
        return observation;
    }

    @Nullable
    public String getExtraData() {
        return extraData;
    }

    public List<Details> getDetails() {
        return details;
    }

    public List<Details> getPaymentMethods() {
        return paymentMethods;
    }

    public Client getClient() {
        return client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "PrinterSpooler{" +
                "id='" + id + '\'' +
                ", employee='" + employee + '\'' +
                ", date='" + date + '\'' +
                ", code='" + code + '\'' +
                ", documentTypeId='" + documentTypeId + '\'' +
                ", observation='" + observation + '\'' +
                ", client=" + client.toString() +
                ", products=" + products +
                ", company=" + company.toString() +
                '}';
    }

    public void print(EscPosCoffee escPosCoffee) {
        switch (this.documentTypeId) {
            case "01" -> {
                escPosCoffee.printElectronicInvoice(PrintElectronicInvoiceDTO.serialize(this));
            }
            case "02" -> {
                escPosCoffee.printPreTicket(PrintPreTicketDTO.serializer(this));
            }
            case "03" -> {
                escPosCoffee.printVoucher(PrintVoucherDTO.serializer(this));
            }
            case "04" -> {
                escPosCoffee.printQuotation(PrintQuotationDTO.serializer(this));
            }
            case "05" -> {
                escPosCoffee.printCommand(PrintCommandDTO.serializer(this));
            }
        }
    }
}
