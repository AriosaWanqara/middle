package com.illarli.middleware.resolver;

import com.illarli.middleware.models.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

public class PrintElectronicInvoiceDTO {
    @NotNull(message = "The transactionNumber is required")
    private String transactionNumber;
    @NotNull(message = "The businessName is required")
    private String businessName;
    private String commercialName;
    @NotNull(message = "The companyRUC is required")
    private String companyRUC;
    @NotNull(message = "The forceAccounting is required")
    private boolean forceAccounting = false;
    private String companyAddress = "N/A";
    private String subsidiaryAddress = "N/A";
    private String companyPhone = "N/A";
    @NotNull(message = "The companyEmail is required")
    @Email(message = "The companyEmail is not valid")
    private String companyEmail;
    @NotNull(message = "The rimpe is required")
    private boolean rimpe = false;
    @NotNull(message = "The withholdingAgent is required")
    private boolean withholdingAgent;
    private String withholdingAgentResolution;
    @NotNull(message = "The clientName is required")
    private String clientName;
    @NotNull(message = "The clientRUC is required")
    private String clientRUC;
    private String clientPhone = "N/A";
    private String clientAddress = "N/A";
    @NotEmpty(message = "The products is required")
    private List<Product> products;
    @NotEmpty(message = "The details is required")
    private Map<String, Double> details;
    @NotEmpty(message = "The details is required")
    private Map<String, Double> paymentMethod;
    @NotNull(message = "The employee is required")
    private String employee;
    @NotNull(message = "The employee is required")
    private String accessPassword;

    public PrintElectronicInvoiceDTO(
            String transactionNumber,
            String businessName,
            String commercialName,
            String companyRUC,
            boolean forceAccounting,
            String companyAddress,
            String subsidiaryAddress,
            String companyPhone,
            String companyEmail,
            boolean rimpe,
            boolean withholdingAgent,
            String withholdingAgentResolution,
            String clientName,
            String clientRUC,
            String clientPhone,
            String clientAddress,
            List<Product> products,
            Map<String, Double> details,
            Map<String, Double> paymentMethod,
            String employee,
            String accessPassword
    ) {
        this.transactionNumber = transactionNumber;
        this.businessName = businessName;
        this.commercialName = commercialName;
        this.companyRUC = companyRUC;
        this.forceAccounting = forceAccounting;
        this.companyAddress = companyAddress != null ? companyAddress : "N/A";
        this.subsidiaryAddress = subsidiaryAddress != null ? clientAddress : "N/A";
        this.companyPhone = companyPhone != null ? clientAddress : "N/A";
        this.companyEmail = companyEmail;
        this.rimpe = rimpe;
        this.withholdingAgent = withholdingAgent;
        this.withholdingAgentResolution = withholdingAgentResolution;
        this.clientName = clientName;
        this.clientRUC = clientRUC;
        this.clientPhone = clientPhone != null ? clientAddress : "N/A";
        this.clientAddress = clientAddress != null ? clientAddress : "N/A";
        this.products = products;
        this.details = details;
        this.paymentMethod = paymentMethod;
        this.employee = employee;
        this.accessPassword = accessPassword;
    }

    public PrinterSpooler createPrinterSpooler() {
        List<Details> spoolerDetails = new ArrayList<>(List.of());
        List<Details> spoolerPaymentMethods = new ArrayList<>(List.of());
        Client client = new Client(this.clientName, this.clientRUC, this.clientPhone, this.clientAddress);
        Company company = new Company(
                this.businessName,
                this.commercialName,
                this.companyRUC,
                this.forceAccounting,
                this.companyAddress,
                this.subsidiaryAddress,
                this.companyPhone,
                this.companyEmail,
                this.rimpe,
                this.withholdingAgent,
                this.withholdingAgentResolution
        );
        this.details.forEach((key, value) -> {
            spoolerDetails.add(new Details(key, value));
        });
        this.paymentMethod.forEach((key, value) -> {
            spoolerPaymentMethods.add(new Details(key, value));
        });
        return new PrinterSpooler(
                UUID.randomUUID().toString(),
                this.employee,
                getDateString(),
                this.transactionNumber,
                "01",
                null,
                this.accessPassword,
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

    private PrintElectronicInvoiceDTO() {
    }

    public String getTransactionNumber() {
        return transactionNumber;
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

    public boolean isRimpe() {
        return rimpe;
    }

    public boolean isWithholdingAgent() {
        return withholdingAgent;
    }

    public String getWithholdingAgentResolution() {
        return withholdingAgentResolution;
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

    public String getAccessPassword() {
        return accessPassword;
    }

    public static PrintElectronicInvoiceDTO serialize(PrinterSpooler printerSpooler) {
        Company company = printerSpooler.getCompany();
        Client client = printerSpooler.getClient();
        Map<String, Double> details = new HashMap<>();
        Map<String, Double> paymentMethods = new HashMap<>();
        printerSpooler.getDetails().forEach(it -> {
            details.put(it.getKeyName(), it.getKeyValue());
        });
        printerSpooler.getPaymentMethods().forEach(it -> {
            paymentMethods.put(it.getKeyName(), it.getKeyValue());
        });
        return new PrintElectronicInvoiceDTO(
                printerSpooler.getCode(),
                company.getBusinessName(),
                company.getCommercialName(),
                company.getRUC(),
                company.isForceAccounting(),
                company.getCompanyAddress(),
                company.getSubsidiaryAddress(),
                company.getCompanyPhone(),
                company.getCompanyEmail(),
                company.isRimpe(),
                company.isWithholdingAgent(),
                company.getWithholdingAgentResolution(),
                client.getClientName(),
                client.getDNI(),
                client.getClientPhone(),
                client.getClientAddress(),
                printerSpooler.getProducts(),
                details,
                paymentMethods,
                printerSpooler.getEmployee(),
                printerSpooler.getExtraData()
        );
    }

    @Override
    public String toString() {
        return "PrintElectronicInvoiceDTO{" +
                "transactionNumber='" + transactionNumber + '\'' +
                ", businessName='" + businessName + '\'' +
                ", commercialName='" + commercialName + '\'' +
                ", companyRUC='" + companyRUC + '\'' +
                ", forceAccounting=" + forceAccounting +
                ", companyAddress='" + companyAddress + '\'' +
                ", subsidiaryAddress='" + subsidiaryAddress + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", rimpe=" + rimpe +
                ", withholdingAgent=" + withholdingAgent +
                ", withholdingAgentResolution='" + withholdingAgentResolution + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientRUC='" + clientRUC + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", products=" + products +
                ", details=" + details +
                ", paymentMethod=" + paymentMethod +
                ", employee='" + employee + '\'' +
                ", accessPassword='" + accessPassword + '\'' +
                '}';
    }
}
