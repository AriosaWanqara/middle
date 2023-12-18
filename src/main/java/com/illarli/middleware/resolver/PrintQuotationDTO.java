package com.illarli.middleware.resolver;

import com.illarli.middleware.models.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public PrinterSpooler createPrinterSpooler() {
        List<Details> spoolerDetails = new ArrayList<>(List.of());
        List<Details> spoolerPaymentMethods = new ArrayList<>(List.of());
        Client client = new Client(this.clientName, this.clientRUC, this.getClientPhone(), this.getClientAddress());
        Company company = new Company(
                this.businessName,
                this.commercialName,
                this.companyRUC,
                this.forceAccounting,
                this.companyAddress,
                this.subsidiaryAddress,
                null,
                this.companyEmail,
                false,
                false,
                null
        );
        this.details.forEach((key, value) -> {
            spoolerDetails.add(new Details(key, value));
        });
        this.details.forEach((key, value) -> {
            spoolerPaymentMethods.add(new Details(key, value));
        });

        return new PrinterSpooler(
                UUID.randomUUID().toString(),
                null,
                getDateString(),
                this.transactionNumber,
                "04",
                null,
                null,
                spoolerDetails,
                spoolerPaymentMethods,
                client,
                this.getProducts(),
                company
        );
    }

    private static String getDateString() {
        Date utilDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        return sdf.format(utilDate);
    }

    public static PrintQuotationDTO serializer(PrinterSpooler printerSpooler) {
        Company company = printerSpooler.getCompany();
        Client client = printerSpooler.getClient();
        Map<String, Double> details = new HashMap<>();
        return new PrintQuotationDTO(
                company.getBusinessName(),
                company.getCommercialName(),
                company.getRUC(),
                company.isForceAccounting(),
                company.getCompanyEmail(),
                company.getCompanyAddress(),
                company.getSubsidiaryAddress(),
                printerSpooler.getCode(),
                client.getClientName(),
                client.getDNI(),
                client.getClientPhone(),
                client.getClientAddress(),
                details,
                printerSpooler.getProducts()
        );
    }

    @Override
    public String toString() {
        return "PrintQuotationDTO{" +
                "businessName='" + businessName + '\'' +
                ", commercialName='" + commercialName + '\'' +
                ", companyRUC='" + companyRUC + '\'' +
                ", forceAccounting=" + forceAccounting +
                ", companyEmail='" + companyEmail + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", subsidiaryAddress='" + subsidiaryAddress + '\'' +
                ", transactionNumber='" + transactionNumber + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientRUC='" + clientRUC + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", details=" + details +
                ", products=" + products +
                '}';
    }
}
