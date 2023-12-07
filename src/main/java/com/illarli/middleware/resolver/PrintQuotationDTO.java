package com.illarli.middleware.resolver;

import com.illarli.middleware.mock.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class PrintQuotationDTO {

    @NotNull(message = "The business name is required")
    private String businessName;
    private String commercialName;
    @NotNull(message = "The company RUC is required")
    private String companyRUC;
    @NotNull(message = "The forceAccounting is required")
    private boolean forceAccounting;
    @NotNull(message = "The companyEmail is required")
    private String companyEmail;
    private String companyAddress = "N/A";
    private String subsidiaryAddress = "N/A";
    @NotNull(message = "The transactionNumber is required")
    private String transactionNumber;
    @NotNull(message = "The clientName is required")
    private String clientName;
    @NotNull(message = "The clientRUC is required")
    private String clientRUC;
    private String clientPhone = "N/A";
    private String clientAddress = "N/A";
    @NotEmpty(message = "The details is required")
    private Map<String, Double> details;
    @NotEmpty(message = "The products is required")
    private List<Product> products;

    public PrintQuotationDTO(
            String businessName,
            String commercialName,
            String companyRUC,
            boolean forceAccounting,
            String companyEmail,
            String companyAddress,
            String subsidiaryAddress,
            String transactionNumber,
            String clientName,
            String clientRUC,
            String clientPhone,
            String clientAddress,
            Map<String, Double> details,
            List<Product> products
    ) {
        this.businessName = businessName;
        this.commercialName = commercialName;
        this.companyRUC = companyRUC;
        this.forceAccounting = forceAccounting;
        this.companyEmail = companyEmail;
        this.companyAddress = companyAddress != null ? companyAddress : "N/A";
        this.subsidiaryAddress = subsidiaryAddress;
        this.transactionNumber = transactionNumber;
        this.clientName = clientName;
        this.clientRUC = clientRUC;
        this.clientPhone = clientPhone != null ? clientPhone : "N/A";
        this.clientAddress = clientAddress != null ? clientAddress : "N/A";
        this.details = details;
        this.products = products;
    }

    private PrintQuotationDTO() {

    }

    public String getBusinessName() {
        return businessName;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public String getCompanyRUC() {
        return companyRUC;
    }

    public boolean isForceAccounting() {
        return forceAccounting;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getSubsidiaryAddress() {
        return subsidiaryAddress;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientRUC() {
        return clientRUC;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public Map<String, Double> getDetails() {
        return details;
    }

    public List<Product> getProducts() {
        return products;
    }
}
