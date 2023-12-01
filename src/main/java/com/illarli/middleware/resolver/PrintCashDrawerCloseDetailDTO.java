package com.illarli.middleware.resolver;

import com.illarli.middleware.mock.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class PrintCashDrawerCloseDetailDTO {

    @Min(value = 1, message = "The close number is required")
    private int closeCashDrawerNumber;
    @NotNull(message = "The businessName is required")
    private String businessName;
    @NotNull(message = "The company RUC is required")
    private String companyRUC;
    private String companyAddress = "N/A";
    @NotNull(message = "The cashDrawerName is required")
    private String cashDrawerName;
    @NotNull(message = "The employee is required")
    private String employee;
    @NotNull(message = "The initialDate is required")
    private String initialDate;
    @NotNull(message = "The endDate is required")
    private String endDate;
    @Min(value = 1, message = "the successfullySelling is required")
    private int successfullySelling;
    @Min(value = 1, message = "the declineSelling is required")
    private int declineSelling;
    @Min(value = 1, message = "the openCashDrawerValue is required")
    private Double openCashDrawerValue;
    @NotEmpty(message = "the income are required")
    private List<Product> income;
    @NotEmpty(message = "the egress are required")
    private List<Product> egress;
    @NotEmpty(message = "the details are required")
    private Map<String, Double> details;

    public PrintCashDrawerCloseDetailDTO(
            int closeCashDrawerNumber,
            String businessName,
            String companyRUC,
            String companyAddress,
            String cashDrawerName,
            String employee,
            String initialDate,
            String endDate,
            int successfullySelling,
            int declineSelling,
            Double openCashDrawerValue,
            List<Product> income,
            List<Product> egress,
            Map<String, Double> details
    ) {
        this.closeCashDrawerNumber = closeCashDrawerNumber;
        this.businessName = businessName;
        this.companyRUC = companyRUC;
        this.companyAddress = companyAddress != null ? companyAddress : "N/A";
        this.cashDrawerName = cashDrawerName;
        this.employee = employee;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.successfullySelling = successfullySelling;
        this.declineSelling = declineSelling;
        this.openCashDrawerValue = openCashDrawerValue;
        this.income = income;
        this.egress = egress;
        this.details = details;
    }

    public int getCloseCashDrawerNumber() {
        return closeCashDrawerNumber;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getCompanyRUC() {
        return companyRUC;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getCashDrawerName() {
        return cashDrawerName;
    }

    public String getEmployee() {
        return employee;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getSuccessfullySelling() {
        return successfullySelling;
    }

    public int getDeclineSelling() {
        return declineSelling;
    }

    public Double getOpenCashDrawerValue() {
        return openCashDrawerValue;
    }

    public List<Product> getIncome() {
        return income;
    }

    public List<Product> getEgress() {
        return egress;
    }

    public Map<String, Double> getDetails() {
        return details;
    }
}
