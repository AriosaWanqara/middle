package com.illarli.middleware.resolver;

import com.illarli.middleware.mock.Product;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class PrintVoucherDTO {
    @NotNull(message = "The businessName is required")
    private String businessName;
    private String commercialName;
    @NotNull(message = "The companyRUC is required")
    private String companyRUC;
    @NotNull(message = "The forceAccounting is required")
    private boolean forceAccounting;
    private String companyAddress;
    private String subsidiaryAddress;
    private String companyPhone;
    @NotNull(message = "The companyEmail is required")
    @Email(message = "The companyEmail is not valid")
    private String companyEmail;
    @NotNull(message = "The transactionNumber is required")
    private String transactionNumber;
    @NotNull(message = "The clientName is required")
    private String clientName;
    @NotNull(message = "The clientRUC is required")
    private String clientRUC;
    private String clientPhone;
    private String clientAddress = "N/A";
    @NotEmpty(message = "The products is required")
    private List<Product> products;
    @NotEmpty(message = "The details is required")
    private Map<String, Double> details;
    @NotEmpty(message = "The details is required")
    private Map<String, Double> paymentMethod;
    @NotNull(message = "The employee is required")
    private String employee;

    public PrintVoucherDTO(
            String businessName,
            String commercialName,
            String companyRUC,
            boolean forceAccounting,
            String companyAddress,
            String subsidiaryAddress,
            String companyPhone,
            String companyEmail,
            String transactionNumber,
            String clientName,
            String clientRUC,
            String clientPhone,
            String clientAddress,
            List<Product> products,
            Map<String, Double> details,
            Map<String, Double> paymentMethod,
            String employee) {
        this.businessName = businessName;
        this.commercialName = commercialName;
        this.companyRUC = companyRUC;
        this.forceAccounting = forceAccounting;
        this.companyAddress = companyAddress != null ? companyAddress : "N/A";
        this.subsidiaryAddress = subsidiaryAddress != null ? subsidiaryAddress : "N/A";
        this.companyPhone = companyPhone != null ? companyPhone : "N/A";
        this.companyEmail = companyEmail;
        this.transactionNumber = transactionNumber;
        this.clientName = clientName;
        this.clientRUC = clientRUC;
        this.clientPhone = clientPhone != null ? clientPhone : "N/A";
        this.clientAddress = clientAddress != null ? clientAddress : "N/A";
        this.products = products;
        this.details = details;
        this.paymentMethod = paymentMethod;
        this.employee = employee;
    }

    private PrintVoucherDTO() {
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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getSubsidiaryAddress() {
        return subsidiaryAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public String getCompanyEmail() {
        return companyEmail;
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

    public List<Product> getProducts() {
        return products;
    }

    public Map<String, Double> getDetails() {
        return details;
    }

    public Map<String, Double> getPaymentMethod() {
        return paymentMethod;
    }

    public String getEmployee() {
        return employee;
    }
}
