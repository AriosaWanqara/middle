package com.illarli.middleware.resolver;

import com.illarli.middleware.mock.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class PrintPreTicketDTO {
    @NotNull(message = "The businessName RUC is required")
    private String businessName;
    private String commercialName;
    private String companyAddress = "N/A";
    private String subsidiaryAddress = "N/A";
    @NotNull(message = "The company RUC is required")
    private String companyRUC;
    @NotNull(message = "The forceAccounting is required")
    private boolean forceAccounting;
    @NotNull(message = "The sell Type is required")
    private String sellType;
    @NotNull(message = "The employee is required")
    private String employee;
    @Min(value = 1, message = "The delivery number is required")
    private int deliveryNumber;
    @NotEmpty(message = "The products is required")
    private List<Product> products;
    @NotEmpty(message = "The products is required")
    private Map<String, Double> details;

    public PrintPreTicketDTO(
            String businessName,
            String commercialName,
            String companyAddress,
            String subsidiaryAddress,
            String companyRUC,
            boolean forceAccounting,
            String sellType,
            String employee,
            int deliveryNumber,
            List<Product> products,
            Map<String, Double> details
    ) {
        this.businessName = businessName;
        this.commercialName = commercialName;
        this.companyAddress = companyAddress != null ? companyAddress : "N/A";
        this.subsidiaryAddress = subsidiaryAddress;
        this.companyRUC = companyRUC;
        this.forceAccounting = forceAccounting;
        this.sellType = sellType;
        this.employee = employee;
        this.deliveryNumber = deliveryNumber;
        this.products = products;
        this.details = details;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public Map<String, Double> getDetails() {
        return details;
    }

    private PrintPreTicketDTO() {
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getSubsidiaryAddress() {
        return subsidiaryAddress;
    }

    public String getCompanyRUC() {
        return companyRUC;
    }

    public boolean isForceAccounting() {
        return forceAccounting;
    }

    public String getSellType() {
        return sellType;
    }

    public String getEmployee() {
        return employee;
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public List<Product> getProducts() {
        return products;
    }
}
